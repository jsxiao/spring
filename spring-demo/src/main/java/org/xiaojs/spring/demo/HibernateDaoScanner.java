package org.xiaojs.spring.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

public class HibernateDaoScanner extends ClassPathBeanDefinitionScanner{

	public HibernateDaoScanner(BeanDefinitionRegistry registry,
			ResourceLoader resourceLoader, BeanNameGenerator nameGenerator) {
		super(registry, false);
		
		setResourceLoader(resourceLoader);
		setBeanNameGenerator(nameGenerator);
		
		addIncludeFilter(new AssignableTypeFilter(HibernateDao.class) {
			@Override
			protected boolean matchClassName(String className) {
				return false;
			}
		});
		
		// exclude package-info.java
		addExcludeFilter(new TypeFilter() {
			public boolean match(MetadataReader metadataReader,
					MetadataReaderFactory metadataReaderFactory)
					throws IOException {
				String className = metadataReader.getClassMetadata()
						.getClassName();
				return className.endsWith("package-info");
			}
		});
	}
	
	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		// TODO Auto-generated method stub
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		
		if(beanDefinitions.isEmpty()){
			logger.warn("No HibernateDao was found in '"
					+ Arrays.toString(basePackages)
					+ "' package. Please check your configuration.");
		}else{
			for(BeanDefinitionHolder holder : beanDefinitions){
				GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
				
				if (logger.isDebugEnabled()) {
					logger.debug("Creating HibernateDaoFactoryBean with name '"
							+ holder.getBeanName() + "' and '"
							+ definition.getBeanClassName() + "' daoInterface");
				}
				
				definition.getPropertyValues().add("daoInterface", definition.getBeanClassName());
				definition.setBeanClass(HibernateDaoFactoryBean.class);
				definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
			}
		}
		
		return beanDefinitions;
	}
}
