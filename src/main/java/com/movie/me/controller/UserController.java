package com.movie.me.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.me.domain.User;
import com.movie.me.domain.Movie;
import com.movie.me.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value="/user", method=RequestMethod.GET, produces="application/json")
	public User getUser(@RequestParam(value="id") String id ) {
		Long uId = new Long(id);
		User user = userService.findById(new Long(uId));

		System.out.println(user.getName());

		return user;
	}

    @RequestMapping(value="/user/search", method=RequestMethod.GET, produces="application/json")
    public List<User> searchForUser(@RequestParam(value="name") String name) {
        return userService.findByNameLike(name);
    }
    
    @RequestMapping(value="/user/view_likes", method=RequestMethod.GET, produces="application/json")
    public List<Movie> moviesLikedByUser(@RequestParam(value="userid") String userid) {
        return userService.retrieveMoviesLikedBy(userid);
    }

    @RequestMapping(value="user/adds_friend", method=RequestMethod.GET, produces="application/json")
    public User addUserFriendsUser(@RequestParam(value="userid1") String userid1, @RequestParam(value="userid2") String userid2) {
        return userService.addUserFriendsUser(userid1, userid2);
    }

    @RequestMapping(value="user/removes_friend", method=RequestMethod.GET, produces="application/json")
    public User userRemovesFriends(@RequestParam(value="userid1") String userid1, @RequestParam(value="userid2") String userid2) {
        return userService.userRemovesFriend(userid1, userid2);
    }

    @RequestMapping(value="user/retrieve_friends", method=RequestMethod.GET, produces="application/json")
    public List<User> retrieveFriendsOf(@RequestParam(value="userid") String userid) {
        return userService.retrieveFriendsOf(userid);
    }
    
    @RequestMapping(value="/user/likes_movie", method=RequestMethod.GET, produces="application/json")
    public Movie userLikesMovie(@RequestParam(value="userid") String userid, @RequestParam(value="imdbid") String imdbid) {
        return userService.addUserLikesMovie(userid, imdbid);
    }

    @RequestMapping(value="/user/unlikes_movie", method=RequestMethod.GET, produces="application/json")
    public Movie userUnlikesMovie(@RequestParam(value="userid") String userid, @RequestParam(value="imdbid") String imdbid) {
        return userService.userUnlikesMovie(userid, imdbid);
    }
}
