package com.bluewater.project_management_tool.model;

import java.time.LocalDate;

import com.bluewater.project_management_tool.constant.CreateAndUpdateDates;
import com.bluewater.project_management_tool.constant.Periority;
import com.bluewater.project_management_tool.constant.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tasks")
@Entity
public class Task extends CreateAndUpdateDates {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String description;
	private LocalDate dueDate;
	private Periority periority;
	private Status status;
	
	@ManyToOne
	private Project project;
	

}
