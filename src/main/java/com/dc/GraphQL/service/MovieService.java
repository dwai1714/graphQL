package com.dc.GraphQL.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dc.GraphQL.model.Director;
import com.dc.GraphQL.model.Movie;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MovieService {
	
    @Value("${api.movieDbApi}")
    private String movieDbApi;
    @Value("${api.omDbApi}")
    private String omDbApi;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ObjectMapper objectMapper;

	public Movie getMovie(String title) throws JsonParseException, JsonMappingException, IOException {
		final String uri = "http://www.omdbapi.com/?apikey=" + omDbApi+ "&t=" + title;

		logger.debug("URL of Attribute Servive is " + uri);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Movie> responseEntity = restTemplate.getForEntity(uri, Movie.class);
		Movie responseData = responseEntity.getBody();
		Integer directorId = getDirectorIdMatchingTitle(responseData.getDirectorName(), responseData.getTitle());
		Director director = getDirector(directorId);
		responseData.setDirector(director);

		String jsonInString = objectMapper.writeValueAsString(responseData);
		logger.info("response Data  " + jsonInString);
		return responseData;
	}

	public Integer getDirectorIdMatchingTitle(String name, String title)
			throws JsonParseException, JsonMappingException, IOException {
		final String uri = "https://api.themoviedb.org/3/search/person?api_key=" + movieDbApi +"&language=en-US&page=1"
				+ "&include_adult=false&query=" + name;

		logger.debug("URL of  Servive is " + uri);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Map> responseEntity = restTemplate.getForEntity(uri, Map.class);
		List responseData = (List) responseEntity.getBody().get("results");
		for (int i = 0; i < responseData.size(); i++) {
			Map data = (Map) responseData.get(i);
			List knownFor = (List) data.get("known_for");
			for (int j = 0; j < knownFor.size(); j++) {
				Map knownForData = (Map) knownFor.get(j);
				knownForData.get("title");
				logger.info("response Data  " + knownForData.get("title"));
				if (knownForData.get("title").equals(title))
					logger.info("ID is   " + data.get("id"));
				return (Integer) data.get("id");

			}

		}

		return 0;
	}

	public Director getDirector(Integer directorId) throws JsonParseException, JsonMappingException, IOException {
		final String uri = "https://api.themoviedb.org/3/person/" + directorId
				+ "?api_key=" + movieDbApi + "&language=en-US\n";

		logger.debug("URL of  Servive is " + uri);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Director> responseEntity = restTemplate.getForEntity(uri, Director.class);
		Director responseData = responseEntity.getBody();
		String jsonInString = objectMapper.writeValueAsString(responseData);
		logger.info("response Data  " + jsonInString);
		return responseData;
	}

	public List<Director> getDirectorByName(String name) throws JsonParseException, JsonMappingException, IOException {
		final String uri = "https://api.themoviedb.org/3/search/person?api_key=" + movieDbApi + "&language=en-US&query="
				+ name + "&page=1&include_adult=false\n";

		logger.debug("URL of  Servive is " + uri);
		RestTemplate restTemplate = new RestTemplate();
		List<Director> directors = new ArrayList();

		ResponseEntity<Map> responseEntity = restTemplate.getForEntity(uri, Map.class);
		List responseData = (List) responseEntity.getBody().get("results");
		for (int i = 0; i < responseData.size(); i++) {
			Map data = (Map) responseData.get(i);
			directors.add(getDirector((Integer) data.get("id")));
		}
		logger.info("response Data  " + directors);
		return directors;
	}

}
