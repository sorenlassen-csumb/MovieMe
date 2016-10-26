package com.movie.me.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.movie.me.domain.User;
import com.movie.me.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCaseInsensitiveSearch() {
        List<User> lowerCase = userRepository.findByNameLike("samuel");
        List<User> upperCase = userRepository.findByNameLike("SAMUEL");

        assertThat(lowerCase, equalTo(upperCase));
    }
}
