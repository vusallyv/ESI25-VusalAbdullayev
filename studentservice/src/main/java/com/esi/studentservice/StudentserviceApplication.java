package com.esi.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentserviceApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("studentservice_db");
		SpringApplication.run(StudentserviceApplication.class, args);
	}
}
