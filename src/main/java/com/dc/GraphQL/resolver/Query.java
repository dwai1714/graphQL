package com.dc.GraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dc.GraphQL.model.Author;
import com.dc.GraphQL.model.Book;
import com.dc.GraphQL.model.Director;
import com.dc.GraphQL.model.Movie;
import com.dc.GraphQL.repository.AuthorRepository;
import com.dc.GraphQL.repository.BookRepository;
import com.dc.GraphQL.service.MovieService;

public class Query implements GraphQLQueryResolver {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private MovieService movieService;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository, MovieService movieService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.movieService = movieService;
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book findOneBook(Long id) {
        return bookRepository.findOne(id);
    }

    public Iterable<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCaseContaining(title);
    }
   

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countBooks() {
        return bookRepository.count();
    }
    public long countAuthors() {
        return authorRepository.count();
    }
    public Movie getMovies(String title) throws Exception{
        return movieService.getMovie(title);
    }
    
    public Iterable<Director> getDirector(String name) throws Exception{
        return movieService.getDirectorByName(name);
    }
}
