package com.movie.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.me.domain.User;
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

		return user;
	}
}
