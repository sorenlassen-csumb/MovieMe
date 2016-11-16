package com.movie.me.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.movie.me.domain.Movie;

@NodeEntity(label="USER")
public class User {
	@GraphId
	private Long id;

    @Property(name="USERID")
    private String userid;

	@Property(name="NAME")
	private String name;

	@Property(name="PHOTO_URI")
	private String photoURI;

	@Property(name="AGE")
	private String age;

	@JsonIgnore
	@Property(name="EMAIL")
	private String email;

	@Relationship(type="LIKES")
	private Set<Movie> moviesLiked;

	@Relationship(type="FRIENDS")
	private Set<User> friends;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Movie> getMoviesLiked() {
		return moviesLiked;
	}

	public void setMoviesLiked(Set<Movie> moviesLiked) {
		this.moviesLiked = moviesLiked;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return this.email;
	}
}
