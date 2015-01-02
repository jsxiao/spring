package spring.ehcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import spring.ehcache.domain.Book;

@Service
public class BookService {

	@Cacheable("books")
	public Book getBook(){
		System.out.println("searching db");
		Book book = new Book();
		return book;
	}
}
