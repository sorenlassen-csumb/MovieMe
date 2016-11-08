package com.movie.me.repository;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

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
    private User clarissa;
    private Movie newHope;
    @Before
    public void initialize() {
        samuel = new User();
        samuel.setUserId("sammy123");
        samuel.setName("Samuel Villavicencio");
        samuel.setEmail("savillavicencio@csumb.edu");
        
        clarissa = new User();
        clarissa.setUserId("clari123");
        clarissa.setName("Clarissa Vazquez");
        clarissa.setEmail("cvasquez-ramo@csumb.edu");
        

        newHope = new Movie();
        newHope.setTitle("Star Wars: Episode IV - A New Hope");
        newHope.setImdbid("0004");

        userRepository.save(samuel);
        userRepository.save(clarissa);
        movieRepository.save(newHope);
        
        
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
    public void testUserLikesMoviesAddsNoDuplicates() {
    	userRepository.addUserLikesMovie("sammy123", "0004");
    	userRepository.addUserLikesMovie("sammy123", "0004");
        List<Movie> result = userRepository.retrieveMoviesLikedBy("sammy123");
        int count = Collections.frequency(result, newHope);
        assertThat(count, equalTo(1));
    }

    @Test
    @DirtiesContext
    public void testRetrieveMoviesLikedByNonexistentUser() {
        List<Movie> result = userRepository.retrieveMoviesLikedBy("Pearce");
        assertThat(result.isEmpty(), is(true));
    }
    
    @Test
    @DirtiesContext
    public void testRetrieveMoviesLikedByExistentUser() {
    	userRepository.addUserLikesMovie("sammy123", "0004");
    	List<Movie> result = userRepository.retrieveMoviesLikedBy("sammy123");
    	assertThat(result.isEmpty(), is(false));
    }
    
    @Test
    @DirtiesContext
    public void testUserLikesNonexistentMovie() {
    	Movie result = userRepository.addUserLikesMovie("sammy123", "0001");
    	assertThat(result, equalTo(null));
    }

    @Test
    @DirtiesContext
    public void testAddUserLikesMovieNonexistentUser() {
        Movie result = userRepository.addUserLikesMovie("Pearce","0004");
        assertThat(result, equalTo(null));
    }

    @Test
    @DirtiesContext
    public void testAddUserLikesMovieExistentUser() {
        Movie result = userRepository.addUserLikesMovie("sammy123","0004");
        assertThat(result, equalTo(newHope));
    } 
    
    @Test
    @DirtiesContext
    public void testAddUserFriendsExistentUser() {
    	User result = userRepository.addUserFriendsUser("sammy123", "clari123");
    	assertThat(result, equalTo(clarissa));
    }
    
    @Test
    @DirtiesContext
    public void testAddUserFriendsNonexistentUser() {
    	User result = userRepository.addUserFriendsUser("sammy123", "Pearce");
    	assertThat(result, equalTo(null));
    }
    
    @Test
    @DirtiesContext
    public void testRetrieveFriendsOfExistentUser() {
    	userRepository.addUserFriendsUser("sammy123", "clari123");
    	List<User> result = userRepository.retrieveFriendsOf("sammy123");
    	assertThat(result.isEmpty(), is(false));
    }

}
