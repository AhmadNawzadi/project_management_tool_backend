package com.bluewater.project_management_tool.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
	
	private ProjectService projectService;
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Project> createProject(@RequestBody Project project){
		projectService.createProject(project);
		return ResponseEntity.status(HttpStatus.CREATED).body(project);
	}
	
	@GetMapping("")
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}
	
	@GetMapping("/user/{userId}")
	public Set<Project> getProjectsByUserId(@PathVariable Long userId) {
		return projectService.getProjectsByUserId(userId);
	}
	
	@GetMapping("/{projectId}")
	public Project getProjectById(@PathVariable Long projectId) {
		return projectService.getById(projectId);
	}
	
//	@PostConstruct
//	public void create() {
//		Project p1 = new Project("Apalosa", "description apalosa", LocalDate.now());
//		projectRepository.save(p1);
//	}

}
