package com.movie.me.service;

import com.movie.me.domain.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findByTitleLike(String title);

    List<Movie> getRecommendationForUser(String userid);
}
