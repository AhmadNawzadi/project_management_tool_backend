package com.bluewater.project_management_tool.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bluewater.project_management_tool.model.Project;
import com.bluewater.project_management_tool.repository.ProjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectService {
	
	private ProjectRepository projectRepository;
	
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}
	
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	public Set<Project> getProjectsByUserId(Long userId){
		return projectRepository.getByUserId(userId);
	}
	
	public Project getById(Long id) {
		return projectRepository.findById(id).get();
	}

	
}
