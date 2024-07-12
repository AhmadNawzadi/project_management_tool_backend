package com.bluewater.project_management_tool.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluewater.project_management_tool.model.Project;
import com.bluewater.project_management_tool.repository.ProjectRepository;
import com.bluewater.project_management_tool.service.ProjectService;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {
	
	private ProjectService projectService;
	private ProjectRepository projectRepository;
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createProject(@RequestBody Project project){
		projectService.createProject(project);
		return ResponseEntity.ok(project);
	}
	
//	@PostConstruct
//	public void create() {
//		Project p1 = new Project("Apalosa", "description apalosa", LocalDate.now());
//		projectRepository.save(p1);
//	}
	
	@GetMapping("")
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}

}
