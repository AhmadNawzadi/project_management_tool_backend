package com.bluewater.project_management_tool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private UserService userService;

	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PostMapping("")
	public User getUser(@RequestBody User user) {
		User existingUser = userService.getUserByUsernameAndPassword(user.getEmail(), user.getPassword());
		return existingUser;
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@GetMapping("")
	public User getUserByEmail(@RequestParam String email) {
		return userService.getUserByEmail(email);
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
