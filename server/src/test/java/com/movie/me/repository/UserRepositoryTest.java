package com.movie.me.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Before
    public void initialize() {
        return;
    }

    @Test
    public void testCaseInsensitiveSearch() {
        List<User> lowerCase = userRepository.findByNameLike("samuel");
        List<User> upperCase = userRepository.findByNameLike("SAMUEL");

        assertThat(lowerCase, equalTo(upperCase));
    }

    @Test
    public void testLikeNoDuplicates() {
        return;
    }
}
