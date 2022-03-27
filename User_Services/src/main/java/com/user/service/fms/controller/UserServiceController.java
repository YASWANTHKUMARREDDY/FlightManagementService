package com.user.service.fms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.fms.service.UserService;
import com.user.service.fms.utils.BaseUrls;
import com.user.service.fms.utils.OwnException;
import com.user.service.fms.utils.OwnResponse;
import com.user.service.fms.vo.UserRegistration;
import com.user.service.fms.vo.UserRegistrationResponse;

@RestController
public class UserServiceController {

	private static final Logger log = LoggerFactory.getLogger(UserServiceController.class);

	@Autowired
	private UserService userService;

	// Registration is only for Traveler 
	@PostMapping(value = BaseUrls.USERREGISTRATION, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> registerUser( @RequestBody UserRegistration userDetails) {
		log.info("User Registration Api Calling..");
		try {
			if (ObjectUtils.isEmpty(userDetails))
				throw new OwnException("Inpus Cant be null....");
			String result = userService.saveUserDetails(userDetails);
			return new OwnResponse<>(result, true, HttpStatus.CREATED);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in UserRegistration " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in UserRegistration " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = BaseUrls.USERLOGIN, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> userLogin(@RequestParam String user, @RequestParam String password) {
		log.info("User login controller..");
		try {
			UserRegistrationResponse result = userService.userLogin(user, password);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in user login " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in User login " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = BaseUrls.CHECKUSER, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> userLogin(@RequestParam String user) {
		log.info("User Checing controller..");
		try {
			String result = userService.checkUserExistsorNot(user);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in user Checking " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in User Checking " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
