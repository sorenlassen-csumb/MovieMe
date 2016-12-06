package com.movie.me.service;

import com.movie.me.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.movie.me.domain.Movie;
import com.movie.me.domain.User;
import com.movie.me.repository.UserRepository;
import org.springframework.core.convert.ConverterNotFoundException;

public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

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
        List<Movie> movies = new ArrayList<>();
        try {
            movies = movieRepository.retrieveMoviesLikedBy(userid);
        } catch(ConverterNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getSourceType());
            System.out.println(e.getTargetType());
        }

        return movies;
    }
    
    public Movie addUserLikesMovie(String userid, String imdbid) {
        return movieRepository.addUserLikesMovie(userid, imdbid);
    }
    
    /*public User addUserFriendsUser(String userid1, String userid2) {
    	return userRepository.addUserFriendsUser(userid1, userid2);
    }

    public User userRemovesFriend(String userid1, String userid2) {
        return userRepository.userRemovesFriend(userid1, userid2);
    }
    
    public List<User> retrieveFriendsOf(String userid) {
    	return userRepository.retrieveFriendsOf(userid);
    }*/

    public Movie userUnlikesMovie(String userId, String imdbid) {
        return movieRepository.userUnlikesMovie(userId, imdbid);
    }

    public List<Movie> getRecommendationForUser(String userid) {
        if( userid == null ) {
            return new ArrayList<>();
        }

        return movieRepository.getRecommendationForUser(userid);
    }

    public User userSignIn(User user) {
        if(userRepository.findByUserId(user.getUserId()) != null) {
            return userRepository.findByUserId(user.getUserId());
        }
        else {
            return userRepository.createUserNode(user.getName(), user.getAge(), user.getEmail(), user.getUserId(), user.getPhotoURI());

        }
    }
}
