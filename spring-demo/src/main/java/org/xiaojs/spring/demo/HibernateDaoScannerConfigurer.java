package org.xiaojs.spring.demo;

import static org.springframework.util.Assert.notNull;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

public class HibernateDaoScannerConfigurer implements
		BeanDefinitionRegistryPostProcessor, ApplicationContextAware,
		BeanNameAware, InitializingBean {

	private String basePackage;
	private boolean processPropertyPlaceHolders;
	private ApplicationContext context;
	private BeanNameGenerator nameGenerator;
	
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("postProcessBeanFactory");
	}

	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry registry) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("postProcessBeanDefinitionRegistry");
		
		HibernateDaoScanner scanner = new HibernateDaoScanner(registry, context, nameGenerator);
		
		scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage,
				ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.context = applicationContext;
	}

	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		notNull(this.basePackage, "Property 'basePackage' is required");
	}
	
	public BeanNameGenerator getNameGenerator() {
		return nameGenerator;
	}

	public void setNameGenerator(BeanNameGenerator nameGenerator) {
		this.nameGenerator = nameGenerator;
	}
	
	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
}
