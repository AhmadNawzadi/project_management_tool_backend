package com.bluewater.project_management_tool.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.bluewater.project_management_tool.constant.CreateAndUpdateDates;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends CreateAndUpdateDates {
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String password;
	
	
	//user - project
	@OneToMany(mappedBy = "user")
	private Set<Project> projects;
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ProjectMember> projectMembers;


}
