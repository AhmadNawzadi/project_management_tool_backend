package com.bluewater.project_management_tool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluewater.project_management_tool.model.User;
import com.bluewater.project_management_tool.repository.UserRepository;
import com.bluewater.project_management_tool.service.UserService;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	private UserRepository userRepository;

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userService.createUser(user);
		return ResponseEntity.ok(user);
	}
	
//	@PostConstruct
//	public void create() {
//		User user1 = new User("ahmad", "1234");
//		User user2 = new User("John", "1234");
//		System.out.println("CREATED AT : " + user1.getCreatedAt());
//		System.out.println("UPDATED AT : " + user1.getCreatedAt() );
//		userRepository.save(user1);
//		userRepository.save(user2);
//		
//	}
}
