package com.bluewater.project_management_tool.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluewater.project_management_tool.service.InvitationService;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/invitation")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class InvitationController {
	
	private InvitationService invitationService;
	
	
	@PostMapping("")
	public String sendEmail(@RequestParam String to, @RequestParam Long projectId) {
		try {
			invitationService.inviteMember(to, projectId);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}

}
