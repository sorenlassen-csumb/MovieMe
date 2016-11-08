package com.movie.me.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.movie.me.domain.User;
import com.movie.me.domain.Movie;

@Repository
public interface UserRepository extends GraphRepository<User> {
    @Query("MATCH (u:USER) " + 
            "WHERE u.NAME =~ ('(?i).*'+{name}+'.*')" + 
            "RETURN u")
    List<User> findByNameLike(@Param("name") String name);

    @Query("MATCH (:USER {USERID:{userid}}) " +
            "-[:LIKES]->(m:MOVIE) " +
            "RETURN m")
    List<Movie> retrieveMoviesLikedBy(@Param("userid") String userid);

    @Query("MATCH (u:USER {USERID:{userid}}), " +
            "(m:MOVIE {IMDBID:{imdbid}}) " +
            "MERGE (u)-[:LIKES]->(m) " +
            "RETURN m")
    Movie addUserLikesMovie(@Param("userid") String userid,
            @Param("imdbid") String imdbid);

    @Query("MATCH (u:USER {USERID:{userid}})-[l:LIKES]-(m:MOVIE {IMDBID:{imdbid}}) " +
            "DELETE l RETURN m")
    Movie userUnlikesMovie(@Param("userid") String userid,
                            @Param("imdbid") String imdbid);
}
