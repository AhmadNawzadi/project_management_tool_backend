package com.bluewater.project_management_tool.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bluewater.project_management_tool.model.Invitation;
import com.bluewater.project_management_tool.model.Project;
import com.bluewater.project_management_tool.model.User;
import com.bluewater.project_management_tool.repository.InvitationRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvitationService {
	
	private JavaMailSender mailSender;
	private InvitationRepository invitationRepository;
	private UserService userService;
	private ProjectMemberService pms;
	private ProjectService pService;
	

	public String inviteMember(String to, Long projectId) throws MessagingException, IOException{
	
	    String template = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/invitation-template.html")));
	
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
		Project project = pService.getById(projectId);
		User user = userService.getUserByEmail(to);
		
	    String emailContent = template.replace("{{projectName}}", project.getName())
	    		.replace("{{userId}}", user.getId().toString())
	    		.replace("{{projectId}}", project.getId().toString());
	   
	    helper.setTo(to);
	    helper.setSubject("Invitation de collaboration");
	    helper.setText(emailContent, true);
	    
		mailSender.send(message);
		
		Invitation invitation = new Invitation(to);
		invitation.setUser(user);
		invitation.setInvitedAt(LocalDateTime.now());
	
		invitationRepository.save(invitation);
		return "email successfull";
		
	}
	
	public void accepteInvitation(Long userId, Long projectId) {
		Invitation invitation = invitationRepository.findByUserId(userId);
		invitation.setAccepted(true);
		pms.createMember(invitation.getUser().getId(), projectId);
	}
	
	public Invitation getInvitation(Long id) {
		return invitationRepository.findById(id).get();
	}
	
	public Invitation findByUserId(Long id) {
		return invitationRepository.findByUserId(id);
	}
	
	public String getEmailByMemberId(Long pmId) {
		return invitationRepository.findEmailByMemberId(pmId);
	}
	
	

}
