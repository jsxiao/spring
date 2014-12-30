package org.xiaojs.spring.demo;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.support.DaoSupport;

public class HibernateDaoFactoryBean<T, K> extends DaoSupport implements FactoryBean<T>, ApplicationContextAware{

	private Class<T> daoInterface;
	private ApplicationContext context;
	private T proxy;
	private Class<K> domainClz;
	
	public void setDaoInterface(Class<T> daoInterface){
		for(Type t : daoInterface.getGenericInterfaces()){
			if(t instanceof ParameterizedType){
				ParameterizedType pt = (ParameterizedType) t;
				System.out.println(pt.getRawType());
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

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.context = applicationContext;
	}

	public T getObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return this.daoInterface;
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void checkDaoConfig() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		notNull(daoInterface, "Property 'daoInterface' is required");
		isTrue(daoInterface.isInterface(),
				"Property 'daoInterface' must be interface");
	}
}
