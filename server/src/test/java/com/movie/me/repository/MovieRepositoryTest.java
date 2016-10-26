package com.movie.me.repository;

import com.movie.me.domain.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;

    @Test
    public void testEmptyResult() {
        List<Movie> result = movieRepository.findByTitleLike("abcdefg");

        assertThat(result.size(), equalTo(0));
    }

    @Test
    public void testCaseSensitiveSearch() {
        List<Movie> lowerCase = movieRepository.findByTitleLike("star");
        List<Movie> upperCase = movieRepository.findByTitleLike("STAR");

        assertThat(lowerCase, equalTo(upperCase));
    }

    @Test
    public void testSuccessfulResult() {
        List<Movie> result = movieRepository.findByTitleLike("star");

        assertTrue(result.size() > 0);
    }
}
