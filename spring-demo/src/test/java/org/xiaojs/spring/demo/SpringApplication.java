package org.xiaojs.spring.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import junit.framework.TestCase;

public class SpringApplication extends TestCase{

	ApplicationContext context;
	
	public SpringApplication(){
		String path = "classpath*:/spring/root-context.xml";
		this.context = new ClassPathXmlApplicationContext(path);
	}
	
	public void testLoading(){
		Assert.notNull(context, "The Application must not be null.");
		
		
	}
}
