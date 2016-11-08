package com.movie.me.repository;

import com.movie.me.beans.Neo4jTestConfiguration;
import com.movie.me.domain.Movie;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class MovieFindByTitleIT {
    @Autowired
    MovieRepository movieRepository;

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
    public void testFindByTitleLikeSuccessfulResult() {
        List<Movie> result = movieRepository.findByTitleLike("star");

        Assert.assertThat(result.size(), Matchers.equalTo(3));
        Assert.assertThat(result, Matchers.containsInAnyOrder(newHope, empireStrikesBack, returnOfTheJedi));
    }

    @Test
    @DirtiesContext
    public void testFindByTitleLikeEmptyResult() {
        List<Movie> result = movieRepository.findByTitleLike("The Phantom Menace");

        Assert.assertThat(result.isEmpty(), Matchers.is(true));
    }

    @Test
    @DirtiesContext
    public void testFindByTitleLikeCaseInsensitive() {
        List<Movie> lowerCase = movieRepository.findByTitleLike("star");
        List<Movie> upperCase = movieRepository.findByTitleLike("STAR");

        Assert.assertThat(lowerCase, Matchers.equalTo(upperCase));
    }
}