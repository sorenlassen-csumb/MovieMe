package com.movie.me.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
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
}
