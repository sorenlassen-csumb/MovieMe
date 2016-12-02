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
    @Query("MATCH (u:USER {USERID:{userid}} " +
            "RETURN u")
    User findByUserId(@Param("userid") String userid);

    @Query("CREATE (u:USER {NAME:{name}, AGE:{age}, EMAIL:{email}}) " +
            "RETURN u")
    User createUserNode(@Param("name") String name, @Param("age") String age, @Param("email") String email);

    @Query("MATCH (u:USER) " +
            "WHERE u.NAME =~ ('(?i).*'+{name}+'.*')" + 
            "RETURN u")
    List<User> findByNameLike(@Param("name") String name);

    @Query("MATCH (:USER {USERID:{userid}}) " +
            "-[:LIKES]->(m:MOVIE) " +
            "RETURN m")
    List<Movie> retrieveMoviesLikedBy(@Param("userid") String userid);

    @Query("MATCH (:USER {USERID:{userid}}) " +
            "-[:LIKES]->(:MOVIE) " +
            "<-[:LIKES]-(:USER) " +
            "-[:LIKES]->(m:MOVIE) " +
            "WITH COUNT(m) AS hits " +
            "RETURN m ORDER BY hits DESC")
    List<Movie> retrieveRecommendationsFor(@Param("userid") String userid);

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
    
    @Query("MATCH (u1:USER {USERID:{userid1}}), " + 
    	   "(u2:USER {USERID:{userid2}}) " + 
    	   "MERGE (u1)-[:FRIENDS]->(u2) " +
    	   "RETURN u2")
    User addUserFriendsUser(@Param("userid1") String userid1, @Param("userid2") String userid2);

    @Query("MATCH (u1:USER {USERID:{userid1}})-[f:FRIENDS]-(u2:USER {USERID:{userid2}}) " +
           "DELETE f return u2")
    User userRemovesFriend(@Param("userid1") String userid1, @Param("userid2") String userid2);
    
    @Query("MATCH (:USER {USERID:{userid}}) " +
            "-[:FRIENDS]->(u:USER) " +
            "RETURN u")
    List<User> retrieveFriendsOf(@Param("userid") String userid);

    @Query("MATCH(u:USER {USERID:{userid}})" +
            "-[:LIKES]->(:MOVIE)<-[:LIKES]-(:USER)" +
            "-[:LIKES]->(m:MOVIE) " +
            "WHERE NOT (u)-[:LIKES]->(m) " +
            "WITH m, COUNT(m) AS hits " +
            "RETURN m ORDER BY hits DESC " +
            "LIMIT 30")
    List<Movie> getRecommendationForUser(@Param("userid") String userid);

}
