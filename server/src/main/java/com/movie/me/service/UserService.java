package com.movie.me.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.me.domain.User;

@Service
public interface UserService {
	User findById(Long id);
    List<User> findByNameLike(String name);
}
