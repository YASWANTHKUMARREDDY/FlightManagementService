package com.user.service.fms.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.fms.entity.UserDetails;

public interface UserServiceRepository extends JpaRepository<UserDetails, Serializable> {
	
	Optional<UserDetails> findByUserId(String userId);
	
	Optional<UserDetails> findByUserIdAndStatus(String userId, Boolean status);

}
