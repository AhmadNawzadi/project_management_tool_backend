package com.bluewater.project_management_tool.service;

import org.springframework.stereotype.Service;

import com.bluewater.project_management_tool.model.Task;
import com.bluewater.project_management_tool.model.User;
import com.bluewater.project_management_tool.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepository;

	public void createUser(User user) {
		userRepository.save(user);
		
	}

}
