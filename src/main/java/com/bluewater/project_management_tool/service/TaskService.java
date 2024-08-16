package com.bluewater.project_management_tool.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.bluewater.project_management_tool.constant.Status;
import com.bluewater.project_management_tool.model.Project;
import com.bluewater.project_management_tool.model.Task;
import com.bluewater.project_management_tool.repository.ProjectRepository;
import com.bluewater.project_management_tool.repository.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
	
	private TaskRepository taskRepository;
	private ProjectRepository projectRepository;
	
	public Task getTaskById(Long taskId) {
		return taskRepository.findById(taskId).get();	
	}
	
	public Set<Task> getTasksByProject(Long projectId) {
		return taskRepository.getByProjectId(projectId);
	}
	
	public void createTask(Task task, Long projectId) {		
		Project project = projectRepository.findById(projectId).get();
		task.setProject(project);
		taskRepository.save(task);
	}
	
	public void updateTask(Long taskId, Task task) {		
		//Project project = projectRepository.findById(projectId).get();
		Task existingTask =  taskRepository.findById(taskId).get();
		task.setId(existingTask.getId());
		
		Project project = existingTask.getProject();
		task.setProject(project);
		taskRepository.save(task);
		System.out.println("UPDADE SUCCESSFULLY");
	}
	
	public Set<Task> getTasksByStatus(Status status) {
		return taskRepository.findByStatus(status);
	}

	public String countTasksByStatus(Long userId) {
		// TODO Auto-generated method stub
		return taskRepository.countTasks(userId);
	}
	
	

}
