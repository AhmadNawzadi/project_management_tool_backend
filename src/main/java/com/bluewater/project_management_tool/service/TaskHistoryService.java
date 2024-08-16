package com.bluewater.project_management_tool.service;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bluewater.project_management_tool.model.ProjectMember;
import com.bluewater.project_management_tool.model.Task;
import com.bluewater.project_management_tool.model.TaskHistory;
import com.bluewater.project_management_tool.repository.TaskHistoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskHistoryService {
	
	private TaskHistoryRepository taskHistoryRepository;
	private TaskService taskService;
	private ProjectMemberService pmService;
	
	public TaskHistory createTaskHistory(Long taskId, Long pmId, String description){
		Task task = taskService.getTaskById(taskId);
		ProjectMember pMember = pmService.getMemberById(pmId);
		TaskHistory taskHistroy = new TaskHistory(task, pMember, description, LocalDateTime.now());
		taskHistoryRepository.save(taskHistroy);
		return taskHistroy;
			
	}
	
	public TaskHistory getHistoryById(Long id) {
		return taskHistoryRepository.findById(id).get();
	}

}
