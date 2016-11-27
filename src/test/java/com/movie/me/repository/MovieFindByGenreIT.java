package com.movie.me.repository;

import com.movie.me.domain.Movie;

import com.movie.me.beans.Neo4jTestConfiguration;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class MovieFindByGenreIT {
    @Autowired
    MovieRepository movieRepository;

    private Movie newHope;

    @Before
    public void initialize() {
        newHope = new Movie();
        newHope.setTitle("Star Wars: Episode IV - A New Hope");
        newHope.setImdbid("0004");
        newHope.setGenre("Sci-Fi");

        movieRepository.save(newHope);
    }

    @Test
    @DirtiesContext
    public void testFindByExistentGenre() {
        List<Movie> result = movieRepository.findByGenreLike("Sci-Fi");

        assertThat(result, Matchers.containsInAnyOrder(newHope));
    }

    @Test
    @DirtiesContext
    public void testFindByNonexistentGenre() {
        List<Movie> result = movieRepository.findByGenreLike("Pearce");

        assertThat(result.isEmpty(), is(true));
    }

    @Test
    @DirtiesContext
    public void testFindByGenreCaseInsensitive() {
        List<Movie> lowerCase = movieRepository.findByGenreLike("sci-fi");
        List<Movie> upperCase = movieRepository.findByGenreLike("SCI-FI");

        assertThat(lowerCase, equalTo(upperCase));
    }

    @Test
    @DirtiesContext
    public void testFindByGenreLike() {
        List<Movie> result = movieRepository.findByGenreLike("sci");

        assertThat(result, Matchers.containsInAnyOrder(newHope));
    }
}
