package com.bluewater.project_management_tool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluewater.project_management_tool.model.TaskHistory;
import com.bluewater.project_management_tool.service.TaskHistoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/history")
@AllArgsConstructor
public class TaskHistoryController {
	
	private TaskHistoryService taskHistoryService;
	
	@PostMapping("/{taskId}/{pmId}")
	@ResponseStatus(HttpStatus.CREATED)
	public TaskHistory createTaskHistory(@PathVariable Long taskId,
			@PathVariable Long pmId, @RequestBody String description){
		String cleanedDescription = description.replace("\"", "");
		return taskHistoryService.createTaskHistory(taskId, pmId, cleanedDescription);
	}
	
	@GetMapping("/{id}")
	public @ResponseBody TaskHistory greeting(@PathVariable Long id) {
		return taskHistoryService.getHistoryById(id);
	}

}
