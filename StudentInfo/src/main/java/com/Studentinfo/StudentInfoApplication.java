package com.Studentinfo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentInfoApplication {
	@Bean
	public ModelMapper modelmapper(){

		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentInfoApplication.class, args);
	}


}
