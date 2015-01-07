package org.xiaojs.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.cache.CacheManager;
import org.xiaojs.dao.annotation.Find;

public class HibernateDaoAnnotationMethod<T> implements HibernateDaoMethod<T>{

	SessionFactory factory;
	CacheManager cacheManager;
	Method method;
	String hql;
	
	public HibernateDaoAnnotationMethod(Method method, Annotation ann, SessionFactory sessionFactory, CacheManager cacheManager) {
		// TODO Auto-generated constructor stub
		this.factory = sessionFactory;
		this.cacheManager = cacheManager;
		this.method = method;
		
		if(ann.annotationType() == Find.class){
			hql = ((Find)ann).value();
		}
		
		
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		Query query = factory.getCurrentSession().createQuery(hql);
		return query.list();
	}
}
