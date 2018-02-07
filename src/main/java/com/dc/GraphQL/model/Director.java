package com.dc.GraphQL.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Director {

	@JsonProperty("birthday")
	private String birthday;
	@JsonProperty("deathday")
	private Object deathday;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("gender")
	private Integer gender;
	@JsonProperty("biography")
	private String biography;
	@JsonProperty("popularity")
	private Float popularity;
	@JsonProperty("place_of_birth")
	private String placeOfBirth;
	@JsonProperty("profile_path")
	private String profilePath;
	@JsonProperty("adult")
	private Boolean adult;
	@JsonProperty("imdb_id")
	private String imdbId;
	@JsonProperty("homepage")
	private String homepage;

	@JsonProperty("birthday")
	public String getBirthday() {
		return birthday;
	}

	@JsonProperty("birthday")
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@JsonProperty("deathday")
	public Object getDeathday() {
		return deathday;
	}

	@JsonProperty("deathday")
	public void setDeathday(Object deathday) {
		this.deathday = deathday;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}


	@JsonProperty("gender")
	public Integer getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@JsonProperty("biography")
	public String getBiography() {
		return biography;
	}

	@JsonProperty("biography")
	public void setBiography(String biography) {
		this.biography = biography;
	}

	@JsonProperty("popularity")
	public Float getPopularity() {
		return popularity;
	}

	@JsonProperty("popularity")
	public void setPopularity(Float popularity) {
		this.popularity = popularity;
	}

	@JsonProperty("place_of_birth")
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	@JsonProperty("place_of_birth")
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	@JsonProperty("profile_path")
	public String getProfilePath() {
		return profilePath;
	}

	@JsonProperty("profile_path")
	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	@JsonProperty("adult")
	public Boolean getAdult() {
		return adult;
	}

	@JsonProperty("adult")
	public void setAdult(Boolean adult) {
		this.adult = adult;
	}

	@JsonProperty("imdb_id")
	public String getImdbId() {
		return imdbId;
	}

	@JsonProperty("imdb_id")
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	@JsonProperty("homepage")
	public String getHomepage() {
		return homepage;
	}

	@JsonProperty("homepage")
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

}