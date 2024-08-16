package com.bluewater.project_management_tool.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_history")
@Data
@NoArgsConstructor
public class TaskHistory {
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "changed_by")
	private ProjectMember pm;
	
	private String changeDescription;
	private LocalDateTime changedAt;
	
	public TaskHistory(Task task, ProjectMember pm, String changeDescription, LocalDateTime changedAt) {
		super();
		this.task = task;
		this.pm = pm;
		this.changeDescription = changeDescription;
		this.changedAt = changedAt;
	}
	
	
	
}
