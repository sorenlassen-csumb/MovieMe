package com.movie.me.repository;

import com.movie.me.beans.Neo4jTestConfiguration;
import com.movie.me.domain.Movie;
import com.movie.me.domain.User;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Clarissa on 11/14/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class UserAddFriendsIT {
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
