package com.movie.me.repository;

import java.util.List;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.Before;

import com.movie.me.domain.User;
import com.movie.me.beans.Neo4jTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class CreateUserIT {
    @Autowired
    private UserRepository userRepository;

    private User sam;
    @Before
    public void initialize() {
        sam = new User();
        sam.setUserId("sam123");
        sam.setName("sam");
        sam.setEmail("svillavicencio@csumb.edu");

        userRepository.save(sam);


        return;
    }

    @Test
    @DirtiesContext
    public void createUserSuccess() {
        User samuel = new User();
        samuel.setUserId("sammy123");
        samuel.setName("Samuel Villavicencio");
        samuel.setAge("21");
        samuel.setPhotoURI("");
        samuel.setEmail("savillavicencio@csumb.edu");

        User result = userRepository.createUserNode(samuel.getName(), samuel.getAge(), samuel.getEmail(), samuel.getUserId(), samuel.getPhotoURI());
        assertThat(result.equals(samuel), is(true));
    }

}
