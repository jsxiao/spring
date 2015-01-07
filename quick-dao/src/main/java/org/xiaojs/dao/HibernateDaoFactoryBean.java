package org.xiaojs.dao;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.support.DaoSupport;

public class HibernateDaoFactoryBean<T, K> extends DaoSupport implements FactoryBean<T>, ApplicationContextAware{

	private ApplicationContext ctx;
	private Class<T> daoInterface;
	private T proxy;
	private Class<K> domainClz;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;
	}

	@SuppressWarnings("unchecked")
	public void setDaoInterface(Class<T> daoInterface){
		this.daoInterface = daoInterface;
		
		for(Type t : daoInterface.getGenericInterfaces()){
			if(t instanceof ParameterizedType){
				ParameterizedType pt = (ParameterizedType) t;
				if(pt.getRawType().equals(HibernateDao.class)){
					domainClz = (Class<K>) pt.getActualTypeArguments()[0];
					break;
				}
			}
		}
		
		if (domainClz == null)
			throw new IllegalArgumentException(
					"Unable to match extract domain class from dao interface '"
							+ daoInterface + "'.");
	}
	
	@SuppressWarnings("unchecked")
	public T getObject() throws Exception {
		if(proxy == null){
			try{
				CacheManager cm = null;
				try{
					cm = ctx.getBean(CacheManager.class);
				}catch(Exception e){}
				
				proxy = (T) Proxy.newProxyInstance(this.daoInterface.getClassLoader(),
						new Class[] { daoInterface }, new HibernateDaoProxy<K>(
								ctx.getBean(SessionFactory.class), cm,
								daoInterface, domainClz));
			}catch(Throwable t){
				logger.error("Error while creating proxy for dao interface '"
						+ this.daoInterface + "'.", t);
				throw new IllegalArgumentException(t);
			}
		}
		return proxy;
	}

	@Override
	public Class<?> getObjectType() {
		return this.daoInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	protected void checkDaoConfig() throws IllegalArgumentException {
		notNull(daoInterface, "Property 'daoInterface' is required");
		isTrue(daoInterface.isInterface(),
				"Property 'daoInterface' must be interface");		
	}
}
