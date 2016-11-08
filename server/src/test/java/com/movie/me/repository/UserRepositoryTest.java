package com.movie.me.repository;

import java.util.List;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.Before;

import com.movie.me.domain.User;
import com.movie.me.domain.Movie;
import com.movie.me.repository.UserRepository;
import com.movie.me.beans.Neo4jTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    MovieRepository movieRepository;

    private User samuel;
    private Movie newHope;
    @Before
    public void initialize() {
        samuel = new User();
        samuel.setUserId("sammy123");
        samuel.setName("Samuel Villavicencio");
        samuel.setEmail("savillavicencio@csumb.edu");
        
        newHope = new Movie();
        newHope.setTitle("Star Wars: Episode IV - A New Hope");
        newHope.setImdbid("0004");
        
        userRepository.save(samuel);
        movieRepository.save(Arrays.asList(newHope));
        
        
        return;
    }

    @Test
    @DirtiesContext
    public void testCaseInsensitiveSearch() {
        List<User> lowerCase = userRepository.findByNameLike("samuel");
        List<User> upperCase = userRepository.findByNameLike("SAMUEL");

        assertThat(lowerCase, equalTo(upperCase));
    }

    @Test
    @DirtiesContext
    public void testLikeNoDuplicates() {
        return;
    }
    
    @Test
    @DirtiesContext
    public void testRetrieveMoviesLikedByNonexistentUser(){
        List<Movie> result = userRepository.retrieveMoviesLikedBy("Pearce");
        assertThat(result.isEmpty(),is(true));
    }
    
    @Test
    @DirtiesContext
    public void testaddUserLikesMovieNonexistentUser(){
        Movie result = userRepository.addUserLikesMovie("Pearce","0004");
        assertThat(result, equalTo(null));
    }
    
    @Test
    @DirtiesContext
    public void testaddUserLikesMovieExistentUser(){
        Movie result = userRepository.addUserLikesMovie("sammy123","0004");
        assertThat(result, equalTo(newHope));
    }


    
    
}
