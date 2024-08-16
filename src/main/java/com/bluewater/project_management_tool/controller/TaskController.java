package com.bluewater.project_management_tool.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluewater.project_management_tool.constant.Status;
import com.bluewater.project_management_tool.model.Task;
import com.bluewater.project_management_tool.service.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
	
	private TaskService taskService;
	
	@PostMapping("/{projectId}")
	public ResponseEntity<Task> createTask(@PathVariable Long projectId, @RequestBody Task task){
		taskService.createTask(task, projectId);
		return ResponseEntity.ok(task);
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
		Task task = taskService.getTaskById(taskId);
		return ResponseEntity.ok(task);
	}
	
	@GetMapping("")
	public ResponseEntity<Set<Task>> getTasksByProjectId(@RequestParam Long projectId){
		Set<Task> tasks = taskService.getTasksByProject(projectId);
		return ResponseEntity.ok(tasks);
	}
	
	@PutMapping("/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task){
		taskService.updateTask(taskId, task);
		return ResponseEntity.ok(task);
	}
	
	@GetMapping("/status")
	public ResponseEntity<Set<Task>> getTasksByStatus(@RequestParam Status status){
		Set<Task> tasks = taskService.getTasksByStatus(status);
		return ResponseEntity.ok(tasks);
	}
	
	@GetMapping("/count/{userId}")
	public ResponseEntity<String> countTasksByStatus(@PathVariable Long userId){
		String count = taskService.countTasksByStatus(userId);
		return ResponseEntity.ok(count);
	}
	
	

	

}
