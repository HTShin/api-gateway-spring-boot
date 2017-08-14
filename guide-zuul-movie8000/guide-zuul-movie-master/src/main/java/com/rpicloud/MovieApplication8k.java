package com.rpicloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class MovieApplication8k {

    @RequestMapping(value = "/ghostbusters")
    public List<String> available() {
        List<String> movies = new ArrayList<>();
        movies.add("Ghostbusters (8k)");
        movies.add("Ghostbusters (9k)");
        return movies;
    }

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication8k.class, args);
	}

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            container.setPort(8000);
        });
    }
}
