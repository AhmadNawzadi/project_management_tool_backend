package com.bluewater.project_management_tool.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "task_history")
@Data
public class TaskHistory {
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Task task;
	
	@ManyToOne
	private User changedBy;
	
	private String changeDescription;
	private LocalDate changedAt;
	
}
