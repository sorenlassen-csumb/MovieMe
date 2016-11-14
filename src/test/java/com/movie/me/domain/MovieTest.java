package com.movie.me.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MovieTest {
    private Movie newHope;

    @Before
    public void initialize() {
        newHope = new Movie();
    }

    @Test
    public void testSetImdbidSuccess() {
        String imdbid = "tt0076759";
        newHope.setImdbid(imdbid);

        assertThat(newHope.getImdbid(), equalTo(imdbid));
    }

    @Test
    public void testSetRatedSuccess() {
        String rated = "PG";
        newHope.setRated(rated);

        assertThat(newHope.getRated(), equalTo(rated));
    }

    @Test
    public void testSetPlotSuccess() {
        String plot = "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a wookiee and two droids to save"
        + " the galaxy from the Empire's world-destroying battle-station, while also attempting to rescue Princess Leia"
        + " from the evil Darth Vader.";
        newHope.setPlot(plot);

        assertThat(newHope.getPlot(), equalTo(plot));
    }

    @Test
    public void testSetWriterSuccess() {
        String writer = "George Lucas";
        newHope.setWriter(writer);

        assertThat(newHope.getWriter(), equalTo(writer));
    }

    @Test
    public void testSetDirectorSuccess() {
        String director = "George Lucas";
        newHope.setDirector(director);

        assertThat(newHope.getDirector(), equalTo(director));
    }

    @Test
    public void testSetActorsSuccess() {
        String actors = "Mark Hamill, Harrison Ford, Carrie Fisher";
        newHope.setActors(actors);

        assertThat(newHope.getActors(), equalTo(actors));
    }

    @Test
    public void testSetGenreSuccess() {
        String genre = "Action";
        newHope.setGenre(genre);

        assertThat(newHope.getGenre(), equalTo(genre));
    }

    @Test
    public void testSetRuntimeSuccess() {
        String runtime = "2h 1min";
        newHope.setRuntime(runtime);

        assertThat(newHope.getRuntime(), equalTo(runtime));
    }

    @Test
    public void testSetPosterSuccess() {
        String poster = "https://images-na.ssl-images-amazon.com/images/M/MV5BZGEzZTExMDEtNjg4OC00NjQxLTk5NTUtNjRkNjA3M"
        + "mYwZjg1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX182_CR0,0,182,268_AL_.jpg";
        newHope.setPoster(poster);

        assertThat(newHope.getPoster(), equalTo(poster));
    }

    @Test
    public void testSetTitleSuccess() {
        String title = "Star Wars: Episode IV - A New Hope";
        newHope.setTitle(title);

        assertThat(newHope.getTitle(), equalTo(title));
    }

    @Test
    public void testSetRatingSuccess() {
        String rating = "8.7";
        newHope.setRating(rating);

        assertThat(newHope.getRating(), equalTo(rating));
    }

    @Test
    public void testSetReleaseDateSuccess() {
        String releaseDate = "25 May 1977";
        newHope.setReleaseDate(releaseDate);

        assertThat(newHope.getReleaseDate(), equalTo(releaseDate));
    }
}
