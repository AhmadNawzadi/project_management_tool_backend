package com.bluewater.project_management_tool.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluewater.project_management_tool.model.Task;
import com.bluewater.project_management_tool.model.TaskAssignement;
import com.bluewater.project_management_tool.service.TaskAssignmentService;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/assignment")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskAssignmentController {
	
	private TaskAssignmentService taskAssignmentService;
	
	@PostMapping("/{taskId}/{pMemberId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void assignTaskToMember(@PathVariable Long taskId, @PathVariable Long pMemberId) throws MessagingException, IOException {
		taskAssignmentService.assignTaskToMember(taskId, pMemberId);
	}
	
	
	@GetMapping("/{taskId}")
	public ResponseEntity<TaskAssignement> getAssignmentByTaskId(@PathVariable Long taskId) {
		TaskAssignement assignement = taskAssignmentService.getAssignmentByTaskId(taskId);
		return ResponseEntity.ok(assignement);
	}

}
