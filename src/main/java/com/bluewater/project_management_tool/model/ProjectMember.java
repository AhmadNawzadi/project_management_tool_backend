package com.bluewater.project_management_tool.model;

import java.util.Set;

import com.bluewater.project_management_tool.constant.CreateAndUpdateDates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "project_members")
@Data
public class ProjectMember extends CreateAndUpdateDates {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String role;
	
	
	
    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
    @ManyToMany
    @JoinTable(
    		name = "project_pms", 
    		joinColumns = @JoinColumn(name = "pm_id"),
    		inverseJoinColumns = @JoinColumn(name = "p_id")
    	)
	private Set<Project> projects;
	

}
