package org.xiaojs.svcimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xiaojs.dao.user.IUserDao;
import org.xiaojs.domain.SdUser;
import org.xiaojs.svc.user.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;

	@Override
	public SdUser findBy(String userName) {
		// TODO Auto-generated method stub
		userDao.findBy(userName);
		return null;
	}
}
