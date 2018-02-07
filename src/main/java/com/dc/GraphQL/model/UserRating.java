package com.dc.GraphQL.model;

import javax.persistence.*;

@Entity
public class UserRating {
	@Id
	@Column(name = "user_rating_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "movie_name", nullable = false)
	private String movieName;

	@Column(name = "user_rating", nullable = false)
	private float userRating;

	public UserRating() {
	}

	public UserRating(Long id) {
		this.id = id;
	}

	public UserRating(String movieName, float userRating) {
		this.movieName = movieName;
		this.userRating = userRating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public float getUserRating() {
		return userRating;
	}

	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}

}
