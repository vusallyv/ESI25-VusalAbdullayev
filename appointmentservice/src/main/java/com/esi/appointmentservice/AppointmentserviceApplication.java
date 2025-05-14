package com.esi.appointmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppointmentserviceApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("appointmentservice_db");
		SpringApplication.run(AppointmentserviceApplication.class, args);
	}
}
