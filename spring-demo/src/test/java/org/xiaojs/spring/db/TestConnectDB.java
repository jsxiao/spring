package org.xiaojs.spring.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jason
 *
 */
public class TestConnectDB {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/spring/*.xml");
		
		// 需要调用一个方法, 否则仅仅加载文件但没有初始化
	}
}
