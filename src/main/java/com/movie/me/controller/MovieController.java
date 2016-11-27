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

    @RequestMapping(value="/movie/search", method=RequestMethod.GET, produces="application/json")
    public List<Movie> searchForMovieByTitle(@RequestParam(value="title") String title) {
        return movieService.findByTitleLike(title);
    }

    @RequestMapping(value="movie/search", method=RequestMethod.GET, produces="applications/json")
    public List<Movie> searchForMovieByRated(@RequestParam(value="rated") String rated) {
        return movieService.findByRated(rated);
    }

    @RequestMapping(value="movie/search", method=RequestMethod.GET, produces="applications/json")
    public List<Movie> searchForMovieByReleaseDate(@RequestParam(value="released") String released) {
        return movieService.findByReleaseDate(released);
    }

    @RequestMapping(value="/movie/search", method=RequestMethod.GET, produces="applications/json")
    public List<Movie> searchForMovieByActor(@RequestParam(value="actor") String actor) {
        return movieService.findByActorLike(actor);
    }

    @RequestMapping(value="movie/search", method=RequestMethod.GET, produces="applications/json")
    public List<Movie> searchForMovieByDirector(@RequestParam(value="director") String director) {
        return movieService.findByDirectorLike(director);
    }

    @RequestMapping(value="movie/search", method=RequestMethod.GET, produces="applications/json")
    public List<Movie> searchForMovieByWriter(@RequestParam(value="writer") String writer) {
        return movieService.findByWriterLike(writer);
    }

    @RequestMapping(value="movie/search", method=RequestMethod.GET, produces="applications/json")
    public List<Movie> searchForMovieByGenre(@RequestParam(value="genre") String genre) {
        return movieService.findByGenreLike(genre);
    }

}
