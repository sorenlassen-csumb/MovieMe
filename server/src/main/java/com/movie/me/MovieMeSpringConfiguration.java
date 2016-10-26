package com.movie.me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@org.springframework.context.annotation.Configuration
@EnableNeo4jRepositories(basePackages = "com.movie.me.repository")
@EnableTransactionManagement
@SpringBootApplication
public class MovieMeSpringConfiguration {
	public static void main(String[] args) throws Exception {
        SpringApplication.run(MovieMeSpringConfiguration.class, args);
    }
}
