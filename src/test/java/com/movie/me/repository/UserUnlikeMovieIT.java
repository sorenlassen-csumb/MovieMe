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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by hargueta on 11/7/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class UserUnlikeMovieIT {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    private User hugo;
    private Movie interstellar;

    @Before
    public void initialize() {
        hugo = new User();

        hugo.setName("Hugo Argueta");
        hugo.setEmail("hugoargueta@gmail.com");
        hugo.setUserId("1001");

        interstellar = new Movie();
        interstellar.setTitle("Interstellar");
        interstellar.setImdbid("tt0030832");

        userRepository.save(hugo);
        movieRepository.save(Arrays.asList(interstellar));
    }

    @Test
    @DirtiesContext
    public void testUserUnlikesMovieExistentLike(){
        userRepository.addUserLikesMovie(hugo.getUserId(), interstellar.getImdbid());
        Movie result = userRepository.userUnlikesMovie(hugo.getUserId(), interstellar.getImdbid());
        assertThat(result, equalTo(interstellar));
    }

    @Test
    @DirtiesContext
    public void testuserUnlikesMovieNonexistentLike(){
        Movie result = userRepository.userUnlikesMovie(hugo.getUserId(), interstellar.getImdbid());
        assertThat(result, equalTo(null));
    }

}
