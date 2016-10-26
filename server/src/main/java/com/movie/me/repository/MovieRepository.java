package com.movie.me.repository;

import com.movie.me.domain.Movie;
import com.movie.me.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends GraphRepository<Movie> {
    @Query("MATCH (m:MOVIE) " +
            "WHERE m.TITLE =~ ('(?i).*'+{title}+'.*')" +
            "RETURN m")
    List<Movie> findByTitleLike(@Param("title") String title);
}
