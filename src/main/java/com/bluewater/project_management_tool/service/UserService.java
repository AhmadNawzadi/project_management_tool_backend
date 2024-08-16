package com.bluewater.project_management_tool.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bluewater.project_management_tool.exception.UserAlreadyExistsException;
import com.bluewater.project_management_tool.exception.UserNotFoundException;
import com.bluewater.project_management_tool.model.User;
import com.bluewater.project_management_tool.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepository;

	public User createUser(User user)  {
		User existingUser = getUserByEmail(user.getEmail());
		
		//List<User> allUsers = userRepository.findAll();

			if(existingUser != null) {
				System.out.println("USER ALREADY IN DB :" + user.getEmail());
				throw new UserAlreadyExistsException("User already exists.");
			}

//		List<User> filteredList = allUsers.stream()
//				.filter(u -> u.getEmail() == user.getEmail())
//				.collect(Collectors.toList());
//		System.out.println("USER ALREADY IN DB :" + filteredList.size());
//		if(filteredList.size() > 0) {
//			throw new UserAlreadyExistsException("User already exists.");
//		}
		return userRepository.save(user);
	}
	
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User getUserByUsernameAndPassword(String email, String password){
		
		User user = userRepository.findByEmailAndPassword(email, password);
		if(user == null) {
			throw new UserNotFoundException("Requested user not exists");
		}
		else {
			return user;
		}	
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
