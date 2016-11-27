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
public class MovieFindByActorsIT {
    @Autowired
    MovieRepository movieRepository;

    private Movie newHope;

    @Before
    public void initialize() {
        newHope = new Movie();
        newHope.setTitle("Star Wars: Episode IV - A New Hope");
        newHope.setImdbid("0004");
        newHope.setActors("Harrison Ford");

        movieRepository.save(newHope);
    }

    @Test
    @DirtiesContext
    public void testFindByExistentActors() {
        List<Movie> result = movieRepository.findByActorLike("Harrison Ford");

        assertThat(result, Matchers.containsInAnyOrder(newHope));
    }

    @Test
    @DirtiesContext
    public void testFindByNonexistentActors() {
        List<Movie> result = movieRepository.findByActorLike("Harrison Poo");

        assertThat(result.isEmpty(), is(true));
    }

    @Test
    @DirtiesContext
    public void testFindByActorsCaseInsensitive() {
        List<Movie> lowerCase = movieRepository.findByActorLike("harrison ford");
        List<Movie> upperCase = movieRepository.findByActorLike("HARRISON FORD");

        assertThat(lowerCase, equalTo(upperCase));
    }

    @Test
    @DirtiesContext
    public void testFindByActorsLike() {
        List<Movie> result = movieRepository.findByActorLike("harrison");

        assertThat(result, Matchers.containsInAnyOrder(newHope));
    }

}
