package com.movie.me.controller;

import com.movie.me.domain.Movie;
import com.movie.me.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class MovieController {
    @Autowired
    MovieService movieService;

    @RequestMapping(value="/movie/search", method= RequestMethod.GET, produces="application/json")
    public List<Movie> searchForMovie(@RequestParam(value="title") String title) {
        return movieService.findByTitleLike(title);
    }
}
