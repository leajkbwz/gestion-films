package com.formation.films.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.formation.films")
@EntityScan(basePackages = "com.formation.films.domain")
@EnableJpaRepositories(basePackages = "com.formation.films.service")
public class FilmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmsApplication.class, args);
    }
}
