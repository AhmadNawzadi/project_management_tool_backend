package com.bluewater.project_management_tool.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bluewater.project_management_tool.model.ProjectMember;
import com.bluewater.project_management_tool.model.Task;
import com.bluewater.project_management_tool.model.TaskAssignement;
import com.bluewater.project_management_tool.model.User;
import com.bluewater.project_management_tool.repository.ProjectMemberRepository;
import com.bluewater.project_management_tool.repository.TaskAssignmentRepository;
import com.bluewater.project_management_tool.repository.TaskRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskAssignmentService {
	
	private TaskAssignmentRepository taskAssignmentRepository;
	private TaskRepository taskRepository;
	private ProjectMemberRepository projectMemberRepository;
	private InvitationService invitationService;
	private JavaMailSender mailSender;
	
	public TaskAssignement getAssignment(Long id) {
		 return taskAssignmentRepository.findById(id).get();
	}
	
	public String sendAssignmentEmail(String to) throws MessagingException, IOException{
	    String template = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/task-assignment.html")));
		
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	   
	    helper.setTo(to);
	    helper.setSubject("Attribution de tâche");
	    helper.setText(template, true);
	    
		mailSender.send(message);
		
		return "";
		
	}
	
	public void assignTaskToMember(Long taskId, Long pMemberId) throws MessagingException, IOException {
		Task task = taskRepository.findById(taskId).get();
		System.out.println("Task id : " + task.getId());
		ProjectMember pMember = projectMemberRepository.findById(pMemberId).get();
		System.out.println("Member id : " + pMember.getId());
		TaskAssignement taskAssignement = new TaskAssignement(task, pMember, LocalDate.now());
		System.out.println("taskAssignement id : " + taskAssignement.getId());
		taskAssignmentRepository.save(taskAssignement);
		String to = invitationService.getEmailByMemberId(pMemberId);
		System.out.println("SEND EMAIL TO : " + to);
		sendAssignmentEmail(to);
		
	}

	public TaskAssignement getAssignmentByTaskId(Long taskId) {
		// TODO Auto-generated method stub
		return taskAssignmentRepository.findByTaskId(taskId);
	}

}
