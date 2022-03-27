package com.apiGateway.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlightManagementApiGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightManagementApiGateWayApplication.class, args);
	}

}
