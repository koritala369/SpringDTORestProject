package com.example.SpringDTORestProject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.SpringDTORestProject")
@EntityScan("com.example.SpringDTORestProject")
public class SpringDtoRestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDtoRestProjectApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
