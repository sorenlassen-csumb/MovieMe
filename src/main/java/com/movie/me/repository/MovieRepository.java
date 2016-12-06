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
            "WHERE m.TITLE =~ ('(?i).*'+{title}+'.*') " +
            "RETURN m")
    List<Movie> findByTitleLike(@Param("title") String title);

    @Query("MATCH (m:MOVIE {RATED:{rated}}) " +
            "RETURN m")
    List<Movie> findByRated(@Param("rated") String rated);

    @Query("MATCH (m:MOVIE {RELEASED:{released}}) " +
            "RETURN m")
    List<Movie> findByReleaseDate(@Param("released") String released);

    @Query("MATCH (m:MOVIE) " +
            "WHERE m.ACTORS =~ ('(?i).*'+{actor}+'.*') " +
            "RETURN m")
    List<Movie> findByActorLike(@Param("actor") String actor);

    @Query("MATCH (m:MOVIE) " +
            "WHERE m.WRITER =~ ('(?i).*'+{writer}+'.*') " +
            "RETURN m")
    List<Movie> findByWriterLike(@Param("writer") String writer);

    @Query("MATCH (m:MOVIE) " +
            "WHERE m.DIRECTOR =~ ('(?i).*'+{director}+'.*') " +
            "RETURN m")
    List<Movie> findByDirectorLike(@Param("director") String director);

    @Query("MATCH (m:MOVIE) " +
            "WHERE m.GENRE =~ ('(?i).*'+{genre}+'.*') " +
            "RETURN m")
    List<Movie> findByGenreLike(@Param("genre") String genre);

    @Query("MATCH(m:MOVIE {IMDBID:{imdbid}})" +
            "<-[l:LIKES]-() " + 
            "RETURN COUNT(l)")
    int countLikesOf(@Param("imdbid") String imdbid);

    @Query("MATCH (:USER {USERID:{userid}}) " +
            "-[:LIKES]->(m:MOVIE) " +
            "RETURN m")
    List<Movie> retrieveMoviesLikedBy(@Param("userid") String userid);

    @Query("MATCH(u:USER {USERID:{userid}})" +
            "-[:LIKES]->(:MOVIE)<-[:LIKES]-(:USER)" +
            "-[:LIKES]->(m:MOVIE) " +
            "WHERE NOT (u)-[:LIKES]->(m) " +
            "WITH m, COUNT(m) AS hits " +
            "RETURN m ORDER BY hits DESC " +
            "LIMIT 30")
    List<Movie> getRecommendationForUser(@Param("userid") String userid);

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
