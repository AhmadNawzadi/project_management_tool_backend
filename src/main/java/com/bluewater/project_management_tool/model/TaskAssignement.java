package com.bluewater.project_management_tool.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "task_assignements")
public class TaskAssignement {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Task task;
	
	@ManyToOne
	private User user; 
	
	private LocalDate assignedAt;

}
