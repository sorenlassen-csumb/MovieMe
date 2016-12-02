package com.movie.me.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.movie.me.domain.User;
import com.movie.me.domain.Movie;

@Service
public interface UserService {
	User findById(Long id);
    List<User> findByNameLike(String name);
    List<Movie> retrieveMoviesLikedBy(String userid);
    Movie addUserLikesMovie(String userid, String imdbid);
    Movie userUnlikesMovie(String userid, String imdbid);
    User addUserFriendsUser(String userid1, String userid2);
    User userRemovesFriend(String userid1, String userid2);
    List<User> retrieveFriendsOf(String userid);
    List<Movie> getRecommendationForUser(String userid);
    User userSignIn(User user);

}
