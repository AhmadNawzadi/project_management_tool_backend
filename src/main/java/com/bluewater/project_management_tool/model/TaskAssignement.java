package com.bluewater.project_management_tool.model;

import java.time.LocalDate;

import embeddable.AssignmentIdTaskId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "task_assignements")
@Data
@NoArgsConstructor
public class TaskAssignement {
	
	@Id
	@GeneratedValue
	private Long id;
	
//    @EmbeddedId
//    private AssignmentIdTaskId id;
	
	@ManyToOne
	private Task task;
	
	@ManyToOne
	private ProjectMember member; 
	
	private LocalDate assignedAt;

	public TaskAssignement(Task task, ProjectMember member, LocalDate assignedAt) {
		this.task = task;
		this.member = member;
		this.assignedAt = assignedAt;
	}
	
	

}
