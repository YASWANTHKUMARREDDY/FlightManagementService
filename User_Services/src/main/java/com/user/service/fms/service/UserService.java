package com.user.service.fms.service;

import com.user.service.fms.vo.UserRegistration;
import com.user.service.fms.vo.UserRegistrationResponse;

public interface UserService {
	
	String saveUserDetails(UserRegistration userRegistration);
	
	UserRegistrationResponse userLogin(String user, String password);

	String checkUserExistsorNot(String user);
}
