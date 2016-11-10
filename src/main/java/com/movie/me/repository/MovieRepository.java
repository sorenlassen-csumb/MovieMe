package com.movie.me.repository;

import com.movie.me.domain.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends GraphRepository<Movie> {
    @Query("MATCH (m:MOVIE {IMDBID:{imdbid}}) " +
            "RETURN m")
    Movie findByImdbId(@Param("imdbid") String imdbid);
    
    @Query("MATCH (m:MOVIE) " +
            "WHERE m.TITLE =~ ('(?i).*'+{title}+'.*')" +
            "RETURN m")
    List<Movie> findByTitleLike(@Param("title") String title);
    
    @Query("MATCH(m:MOVIE {IMDBID:{imdbid}})" +
            "<-[l:LIKES]-() " + 
            "RETURN COUNT(l)")
    int countLikesOf(@Param("imdbid") String imdbid);

    @Query("MATCH(u:USER {USERID:{userid}})" +
            "-[:LIKES]->(:MOVIE)<-[:LIKES]-(:USER)" +
            "-[:LIKES]->(m:MOVIE) " +
            "WHERE NOT (u)-[:LIKES]->(m) " +
            "WITH m, COUNT(m) AS hits " +
            "RETURN m ORDER BY hits DESC " +
            "LIMIT 30")
    List<Movie> getRecommendationForUser(@Param("userid") String userid);
}
