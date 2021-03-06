package com.movie.me.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTest {
    private User samuel;
    private Movie newHope;

    @Before
    public void initialize() {
        samuel = new User();
        newHope = new Movie();
    }

    @Test
    public void testSetUserIdSuccess() {
        String userid = "mm1729384";
        samuel.setUserId(userid);

        assertThat(samuel.getUserId(), equalTo(userid));
    }

    @Test
    public void testSetNameSuccess() {
        String name = "Samuel";
        samuel.setName(name);

        assertThat(samuel.getName(), equalTo(name));
    }

    @Test
    public void testSetEmailSuccess() {
        String email = "samuelrey010@gmail.com";
        samuel.setEmail(email);

        assertThat(samuel.getEmail(), equalTo(email));
    }

    @Test
    public void testSetMoviesLikedSuccess() {
        HashSet<Movie> moviesLiked = new HashSet<>();
        moviesLiked.add(newHope);
        samuel.setMoviesLiked(moviesLiked);

        assertThat(samuel.getMoviesLiked(), equalTo(moviesLiked));
    }
}
