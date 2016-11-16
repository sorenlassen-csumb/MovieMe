package com.movie.me.repository;

import com.movie.me.beans.Neo4jTestConfiguration;
import com.movie.me.domain.Movie;
import com.movie.me.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class UserRemovesFriendsIT {
    @Autowired
    private UserRepository userRepository;

    private User samuel;
    private User clarissa;
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

        userRepository.save(samuel);
        userRepository.save(clarissa);

        return;
    }

    @Test
    @DirtiesContext
    public void testUserRemovesExistentFriend() {
        userRepository.addUserFriendsUser(samuel.getUserId(), clarissa.getUserId());
        User result = userRepository.userRemovesFriend(samuel.getUserId(), clarissa.getUserId());
        assertThat(result, equalTo(clarissa));
    }

    @Test
    @DirtiesContext
    public void testUserRemovesNonexistentFriend() {
        User result = userRepository.userRemovesFriend(samuel.getUserId(), "Pearce");
        assertThat(result, equalTo(null));
    }

}
