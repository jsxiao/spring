package org.xiaojs.dao;

import java.util.List;

import org.xiaojs.domain.User;

public interface IUserDao{

	public List<User> findUser();
	
	public List<User> findUser(String userName);
}