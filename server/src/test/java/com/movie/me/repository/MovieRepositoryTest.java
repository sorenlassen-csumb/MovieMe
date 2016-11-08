package com.movie.me.repository;

import com.movie.me.domain.Movie;
import com.movie.me.domain.User;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@ActiveProfiles(profiles = "test")
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    private Movie newHope;
    private Movie empireStrikesBack;
    private Movie returnOfTheJedi;

    @Before
    public void initialize() {
        newHope = new Movie();
        newHope.setTitle("Star Wars: Episode IV - A New Hope");
        newHope.setImdbid("0004");

        empireStrikesBack = new Movie();
        empireStrikesBack.setTitle("Star Wars: Episode V - Empire Strikes Back");
        empireStrikesBack.setImdbid("0008");

        returnOfTheJedi = new Movie();
        returnOfTheJedi.setTitle("Star Wars: Episode VI - Return of the Jedi");
        returnOfTheJedi.setImdbid("0016");

        movieRepository.save(Arrays.asList(newHope, empireStrikesBack, returnOfTheJedi));
    }

    @Test
    @DirtiesContext
    public void testFindByImdbIdSuccessfulResult() {
        Movie result = movieRepository.findByImdbId("0004");

        assertThat(result.equals(newHope), is(true));
    }

    @Test
    @DirtiesContext
    public void testFindByImdbIdEmptyResult() {
        Movie result = movieRepository.findByImdbId("0001");

        assertThat(result, is(nullValue()));
    }

    @Test
    @DirtiesContext
    public void testFindByTitleLikeSuccessfulResult() {
        List<Movie> result = movieRepository.findByTitleLike("star");

        assertThat(result.size(), equalTo(3));
        assertThat(result, containsInAnyOrder(newHope, empireStrikesBack, returnOfTheJedi));
    }

    @Test
    @DirtiesContext
    public void testFindByTitleLikeEmptyResult() {
        List<Movie> result = movieRepository.findByTitleLike("The Phantom Menace");

        assertThat(result.isEmpty(), is(true));
    }

    @Test
    @DirtiesContext
    public void testFindByTitleLikeCaseInsensitive() {
        List<Movie> lowerCase = movieRepository.findByTitleLike("star");
        List<Movie> upperCase = movieRepository.findByTitleLike("STAR");

        assertThat(lowerCase, equalTo(upperCase));
    }

    @Test
    @DirtiesContext
    public void testCountLikesOfMovieIsZero() {
        int count = movieRepository.countLikesOf("0004");

        assertThat(count, equalTo(0));
    }

    @Test
    @DirtiesContext
    public void testCountLikesOfMovieIsMoreThanZero() {
        User samuel = new User();
        samuel.setMoviesLiked(new HashSet<Movie>(Arrays.asList(newHope)));
        userRepository.save(samuel);

        int count = movieRepository.countLikesOf("0004");

        assertThat(count, equalTo(1));
    }
}
