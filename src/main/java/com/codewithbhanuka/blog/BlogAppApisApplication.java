package com.codewithbhanuka.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

//	The @Bean annotation in Spring Framework is used to declare a
//	method as a bean-producing method. When Spring scans your
//	configuration and encounters methods annotated with @Bean,
//	it registers the return value of that method as a bean within the
//	Spring application context.
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	//model mapper for user to userdto and userdto to user
}

