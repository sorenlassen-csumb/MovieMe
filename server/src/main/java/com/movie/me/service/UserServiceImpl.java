package com.movie.me.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.movie.me.domain.User;
import com.movie.me.repository.UserRepository;

public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findOne(id);
	}
}
