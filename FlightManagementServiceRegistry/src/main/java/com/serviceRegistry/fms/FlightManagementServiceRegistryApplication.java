package com.serviceRegistry.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FlightManagementServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightManagementServiceRegistryApplication.class, args);
	}

}
