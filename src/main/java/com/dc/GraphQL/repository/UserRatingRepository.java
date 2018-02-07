package com.dc.GraphQL.repository;

import org.springframework.data.repository.CrudRepository;

import com.dc.GraphQL.model.UserRating;

public interface UserRatingRepository extends CrudRepository<UserRating, Long> {
	UserRating findByMovieName(String name);

}
