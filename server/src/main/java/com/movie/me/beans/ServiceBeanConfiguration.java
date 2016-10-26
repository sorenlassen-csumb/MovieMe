package com.movie.me.beans;

import com.movie.me.service.MovieService;
import com.movie.me.service.MovieServiceImpl;
import com.movie.me.service.UserService;
import com.movie.me.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfiguration {

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean
    public MovieService getMovieService() {
        return new MovieServiceImpl();
    }
}
