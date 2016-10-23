package com.movie.me.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.movie.me.domain.User;

@Repository
public interface UserRepository extends GraphRepository<User> {
    @Query("MATCH (u:User) " + 
            "WHERE u.NAME =~ ('(?i).*'+{name}+'.*')" + 
            "RETURN u")
    List<User> findByNameLike(@Param("name") String name);
}
