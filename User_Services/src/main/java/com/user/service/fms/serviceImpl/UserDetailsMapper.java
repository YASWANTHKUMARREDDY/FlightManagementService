package com.user.service.fms.serviceImpl;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.user.service.fms.entity.UserDetails;
import com.user.service.fms.utils.OwnException;
import com.user.service.fms.utils.Roles;
import com.user.service.fms.vo.UserRegistration;
import com.user.service.fms.vo.UserRegistrationResponse;

@Component
public class UserDetailsMapper {

	public UserDetails converttoEntity(UserRegistration vo) {

		UserDetails dto = new UserDetails();
		dto.setFirstName(vo.getFirstName());
		dto.setLastName(vo.getLastName());
		if (!vo.getPassword().equalsIgnoreCase(vo.getConfirmPassword()))
			throw new OwnException("Password and Confirm Password must be same.");
		dto.setPassword(vo.getPassword());
		dto.setUserId(vo.getUserId());
		dto.setRole(Roles.Traveler.getName());
		dto.setStatus(true);
		dto.setCreatedDate(LocalDate.now());

		return dto;
	}

	public UserRegistrationResponse convertToResponse(UserDetails dto) {

		UserRegistrationResponse vo = new UserRegistrationResponse();
		vo.setFirstName(dto.getFirstName());
		vo.setLastName(dto.getLastName());
		vo.setUserId(dto.getUserId());
		vo.setRole(dto.getRole());

		return vo;
	}

}
