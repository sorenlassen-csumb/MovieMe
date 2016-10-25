package com.movie.me.service;

import com.movie.me.domain.Movie;
import com.movie.me.domain.User;

import java.util.List;

/**
 * Created by hargueta on 10/25/16.
 */
public interface MovieService {
    List<Movie> findByTitleLike(String title);
}
