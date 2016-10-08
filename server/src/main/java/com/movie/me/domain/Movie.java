package com.movie.me.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label="MOVIE")
public class Movie {
	@GraphId
	private Long id;
	
	@Property(name="TITLE")
	private String title;
	
	@Property(name="RATING")
	private String rating;
	
	@Property(name="RELEASE_DATE")
	private String releaseDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	@Override
	public String toString() {
		return this.title;
	}
}
