package com.dc.GraphQL.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dc.GraphQL.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByTitleIgnoreCaseContaining(String title);

}
