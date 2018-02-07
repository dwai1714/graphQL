package com.dc.GraphQL.repository;

import org.springframework.data.repository.CrudRepository;

import com.dc.GraphQL.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
