package org.xiaojs.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xiaojs.dao.user.IUserDao;
import org.xiaojs.domain.SdUser;
import org.xiaojs.svc.user.IUserService;
import org.xiaojs.svcimpl.user.UserServiceImpl;

public class ScanerBean {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/root-context.xml");
		IUserService userService = context.getBean(UserServiceImpl.class);
		SdUser user = userService.findBy("");
		System.out.println(user);
	}
}
