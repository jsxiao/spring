package org.xiaojs.dao;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.hibernate.SessionFactory;
import org.springframework.cache.CacheManager;
import org.xiaojs.dao.annotation.Find;
import org.xiaojs.dao.annotation.Update;

public class HibernateDaoProxy<T> implements InvocationHandler, Serializable {
	private static final long serialVersionUID = 1L;

	SessionFactory sessionFactory;
	CacheManager cacheManager;
	Class<T> claz;
	Hashtable<Method, HibernateDaoMethod<T>> htDaoMethods = new Hashtable<Method, HibernateDaoMethod<T>>();
	
	public HibernateDaoProxy(SessionFactory sessionFactory, CacheManager cacheManager, Class<?> daoInterface, Class<T> claz) {
		// TODO Auto-generated constructor stub
		this.sessionFactory = sessionFactory;
		this.cacheManager = cacheManager;
		this.claz = claz;
		
		// 获取接口中定义的方法
		for(Method method : daoInterface.getMethods()){
			boolean found = false;
			if(!found){
				for(Annotation ann : method.getDeclaredAnnotations()){
					Class<?> t = ann.annotationType();
					if(t == Find.class || t == Update.class){
						htDaoMethods.put(method, new HibernateDaoAnnotationMethod<T>(method, ann, sessionFactory, cacheManager));
						found = true;
						break;
					}
				}
			}
			
			if (!found)
				throw new IllegalArgumentException("Unsupported method "
						+ daoInterface.getName() + "." + method.getName() + ".");
		}
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		HibernateDaoMethod<T> hdm = htDaoMethods.get(method);
		if(hdm != null){
			hdm.invoke(proxy, method, args);
		}
		return null;
	}
}
