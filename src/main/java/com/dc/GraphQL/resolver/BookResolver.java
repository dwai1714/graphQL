package com.dc.GraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.dc.GraphQL.model.Author;
import com.dc.GraphQL.model.Book;
import com.dc.GraphQL.repository.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {
    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findOne(book.getAuthor().getId());
    }
}
