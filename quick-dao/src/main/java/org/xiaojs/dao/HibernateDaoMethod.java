package org.xiaojs.dao;

import java.lang.reflect.Method;

public interface HibernateDaoMethod<T> {

	public Object invoke(Object proxy, Method method, Object[] args);
}
