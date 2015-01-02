package org.xiaojs.spring.ehcache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.ehcache.BookService;

public class EhCacheTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/spring/*.xml");
		BookService service = applicationContext.getBean(BookService.class);
		service.getBook();
		
		int count = 0;
		while(true){
			try {
				Thread.sleep(5000);
				count++;
				System.out.println(String.format("第%s次查询", count));
				service.getBook();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
