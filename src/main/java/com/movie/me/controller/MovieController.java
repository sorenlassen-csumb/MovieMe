package com.movie.me.controller;

import com.movie.me.domain.Movie;
import com.movie.me.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class MovieController {
    @Autowired
    MovieService movieService;

    @RequestMapping(value="/movie/search", method=RequestMethod.GET, produces="application/json")
    public List<Movie> searchForMovie(@RequestParam(value="property") String property, @RequestParam(value="value") String value) {
        switch(property) {
            case "title":
                return movieService.findByTitleLike(value);
            case "rated":
                return movieService.findByRated(value);
            case "released":
                return movieService.findByReleaseDate(value);
            case "actor":
                return movieService.findByActorLike(value);
            case "director":
                return movieService.findByDirectorLike(value);
            case "writer":
                return movieService.findByWriterLike(value);
            case "genre":
                return movieService.findByGenreLike(value);
            default:
                return new ArrayList<Movie>();
        }
    }

}
