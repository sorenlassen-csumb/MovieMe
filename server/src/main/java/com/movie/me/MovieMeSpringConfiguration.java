package com.movie.me;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.movie.me.service.UserService;
import com.movie.me.service.UserServiceImpl;

@EnableAutoConfiguration
@org.springframework.context.annotation.Configuration
@EnableNeo4jRepositories(basePackages = "com.movie.me.repository")
@EnableTransactionManagement
@SpringBootApplication
public class MovieMeSpringConfiguration extends Neo4jConfiguration {
	@Bean
	public UserService getUserService() {
		return new UserServiceImpl();
	}

	@Bean
	public Configuration getConfiguration() {
	   Configuration config = new Configuration();
	   config
	       .driverConfiguration()
	       .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
	       .setURI("http://neo4j:MovieMe@localhost:7474");
	   return config;
	}

	@Bean
	public SessionFactory getSessionFactory() {
	    return new SessionFactory(getConfiguration(), "com.movie.me.domain");
	}

	public static void main(String[] args) throws Exception {
        SpringApplication.run(MovieMeSpringConfiguration.class, args);
    }

}
