package com.movie.me.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.movie.me.domain.Movie;
import com.movie.me.domain.User;
import com.movie.me.repository.UserRepository;

public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findOne(id);
	}

    public List<User> findByNameLike(String name) {
        if( name.length() > 0 ) {
            return userRepository.findByNameLike(name);
        }

        return new ArrayList<User>();
    }

    public List<Movie> retrieveMoviesLikedBy(String userid) {
        return userRepository.retrieveMoviesLikedBy(userid);
    }
    
    public Movie addUserLikesMovie(String userid, String imdbid) {
        return userRepository.addUserLikesMovie(userid, imdbid);
    }
    
    public User addUserFriendsUser(String userid1, String userid2) {
    	return userRepository.addUserFriendsUser(userid1, userid2);
    }

    public User userRemovesFriend(String userid1, String userid2) {
        return userRepository.userRemovesFriend(userid1, userid2);
    }
    
    public List<User> retrieveFriendsOf(String userid) {
    	return userRepository.retrieveFriendsOf(userid);
    }

    public Movie userUnlikesMovie(String userId, String imdbid) {
        return userRepository.userUnlikesMovie(userId, imdbid);
    }

    public List<Movie> getRecommendationForUser(String userid) {
        if( userid == null ) {
            return new ArrayList<>();
        }

        return userRepository.getRecommendationForUser(userid);
    }
}
