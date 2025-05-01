package com.example.FleetGPS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FleetGpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetGpsApplication.class, args);
	}

}
