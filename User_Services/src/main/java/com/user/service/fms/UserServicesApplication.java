package com.user.service.fms;

import java.time.LocalDate;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.user.service.fms.entity.UserDetails;
import com.user.service.fms.repository.UserServiceRepository;
import com.user.service.fms.utils.Roles;

@SpringBootApplication
@EnableEurekaClient
public class UserServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServicesApplication.class, args);
	}

	@Autowired
	UserServiceRepository repository;

	// Creating Admin Users At the Time of Application Start.
	@PostConstruct
	public void saveAdminUsers() {
		
		UserDetails adminUser = new UserDetails(null, "test", "user", "Admin1", "admin", Roles.Admin.getName(),
				LocalDate.now(), true);
		UserDetails adminUser1 = new UserDetails(null, "test", "user1", "Admin2", "admin", Roles.Admin.getName(),
				LocalDate.now(), true);
		repository.saveAll(Arrays.asList(adminUser, adminUser1));
	}
}
