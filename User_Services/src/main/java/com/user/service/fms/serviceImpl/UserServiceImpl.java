package com.user.service.fms.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.user.service.fms.entity.UserDetails;
import com.user.service.fms.repository.UserServiceRepository;
import com.user.service.fms.service.UserService;
import com.user.service.fms.utils.OwnException;
import com.user.service.fms.utils.Roles;
import com.user.service.fms.vo.UserRegistration;
import com.user.service.fms.vo.UserRegistrationResponse;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserServiceRepository userServiceRepository;

	@Autowired
	UserDetailsMapper userDetailsMapper;

	@Override
	public String saveUserDetails(UserRegistration input) {
		if (!StringUtils.hasLength(input.getUserId()) || !StringUtils.hasLength(input.getPassword())
				|| !StringUtils.hasLength(input.getConfirmPassword()))
			throw new OwnException("Provide User Name and Passwords...");
		log.info("Checking User Name Already Exists or not..");
		Optional<UserDetails> userDetails = userServiceRepository.findByUserId(input.getUserId());
		if (userDetails.isPresent())
			throw new OwnException(
					"User Details Already Exists with userName " + input.getUserId() + " try with another username");
		UserDetails dto = userDetailsMapper.converttoEntity(input);
		log.info("Saving the DataBase Object..");
		userServiceRepository.save(dto);
		return "Account Succesfully Registered with user name " + input.getUserId();
	}

	@Override
	public UserRegistrationResponse userLogin(String user, String password) {
		if (!StringUtils.hasLength(user) || !StringUtils.hasLength(password))
			throw new OwnException("Provide User Name and Passwords...");
		log.info("Checking User Data in Database..");
		Optional<UserDetails> userDetails = userServiceRepository.findByUserIdAndStatus(user, Boolean.TRUE);
		if (!userDetails.isPresent())
			throw new OwnException("No UserDetails Found with user name " + user);
		if (!userDetails.get().getPassword().equalsIgnoreCase(password))
			throw new OwnException("Password Not Matching......");
		log.info("Converting DataBase Object into VO Object..");
		UserRegistrationResponse result = userDetailsMapper.convertToResponse(userDetails.get());
		return result;
	}

	@Override
	public String checkUserExistsorNot(String user) {
		log.info("Checking Admin User....");
		if (!StringUtils.hasLength(user))
			throw new OwnException("Provide User Name and Passwords...");
		Optional<UserDetails> userDetails = userServiceRepository.findByUserIdAndStatus(user, Boolean.TRUE);
		if (!userDetails.isPresent())
			throw new OwnException("No Active Users Found with user name " + user);
		if(!userDetails.get().getRole().equalsIgnoreCase(Roles.Admin.getName()))
			throw new OwnException("Un Authorized User to Access This Service..");
		return userDetails.get().getUserId();
	}

}
