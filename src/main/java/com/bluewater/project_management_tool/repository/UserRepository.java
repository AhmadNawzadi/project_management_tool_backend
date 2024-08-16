package com.bluewater.project_management_tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluewater.project_management_tool.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

}
