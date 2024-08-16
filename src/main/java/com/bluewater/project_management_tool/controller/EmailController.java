package com.bluewater.project_management_tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bluewater.project_management_tool.service.InvitationService;


@Controller
@RequestMapping("/email")
public class EmailController {
	

	private InvitationService invitationService;
	
	@Autowired
	public EmailController(InvitationService invitationService) {
		super();
		this.invitationService = invitationService;
	}
	
	
	@GetMapping("/{userId}/{projectId}")
	public ModelAndView accepteInvitation(@PathVariable Long userId, @PathVariable Long projectId) {
		invitationService.accepteInvitation(userId, projectId);
        String redirectUrl = "http://localhost:4200";
        return new ModelAndView("redirect:" + redirectUrl);
	}
	




}
