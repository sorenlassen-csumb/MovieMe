package com.movie.me.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.me.domain.User;

public interface UserService {
	User findById(Long id);
}
