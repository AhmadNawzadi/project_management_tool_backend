package com.bluewater.project_management_tool.service;

import org.springframework.stereotype.Service;

import com.bluewater.project_management_tool.model.Project;
import com.bluewater.project_management_tool.repository.ProjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectService {
	
	private ProjectRepository projectRepository;
	
	public void createProject(Project project) {
		projectRepository.save(project);
	}

}
