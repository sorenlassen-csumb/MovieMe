package com.movie.me.service;

import com.movie.me.domain.Movie;
import com.movie.me.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    public List<Movie> findByTitleLike(String title) {
        if( title.length() > 0 ) {
            return movieRepository.findByTitleLike(title);
        }

        return new ArrayList<Movie>();
    }

    public List<Movie> getRecommendationForUser(String userid) {
        if( userid == null ) {
            return new ArrayList<>();
        }

        return movieRepository.getRecommendationForUser(userid);
    }
}
