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
public class BookApplication {
    
    @RequestMapping(value = "/bookers")
    public List<String> availableBook() {
        List<String> books = new ArrayList<>();
        books.add("bookers (2005)");
        books.add("bookers (2016)");
        return books;
    }

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            container.setPort(9001);
        });
    }
}
