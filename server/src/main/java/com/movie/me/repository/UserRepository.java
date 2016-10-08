package com.movie.me.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.movie.me.domain.User;

@Repository
public interface UserRepository extends GraphRepository<User> {
}
