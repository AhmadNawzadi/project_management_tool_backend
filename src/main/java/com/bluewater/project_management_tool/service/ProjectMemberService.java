package com.bluewater.project_management_tool.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.bluewater.project_management_tool.constant.Role;
import com.bluewater.project_management_tool.dto.ProjectMemberDTO;
import com.bluewater.project_management_tool.model.Invitation;
import com.bluewater.project_management_tool.model.ProjectMember;
import com.bluewater.project_management_tool.model.User;
import com.bluewater.project_management_tool.model.Project;
import com.bluewater.project_management_tool.repository.ProjectMemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class ProjectMemberService {
	
	private ProjectMemberRepository projectMemberRepository;
	private InvitationService invitationService;
	private UserService userService;
	private ProjectService projectService;
	
	
	public ProjectMemberService(@Lazy ProjectMemberRepository projectMemberRepository, @Lazy InvitationService invitationService,
			@Lazy UserService userService, @Lazy ProjectService projectService) {
		super();
		this.projectMemberRepository = projectMemberRepository;
		this.invitationService = invitationService;
		this.userService = userService;
		this.projectService = projectService;
	}

	public void addProjectMember(Long userId) {
		Invitation invitation = invitationService.findByUserId(userId);
		User user = userService.getUser(userId);
		
		if(invitation != null & invitation.isAccepted()) {
			ProjectMember projectMember = new ProjectMember(Role.OBSERVER, user);
			projectMemberRepository.save(projectMember);
		}
		else {
			throw new  NullPointerException("Invitation not accepted yet. ");
		}	
	}
	
	public ProjectMember getMemberById(Long pmId) {
		return projectMemberRepository.findById(pmId).get();
	}
	
//	public void assignRole(Long userId, Long project_id, Role role) {
//		ProjectMember pm = projectMemberRepository.getByUserId(userId);
//		pm.setRole(role);
//		projectMemberRepository.save(pm);
//	}
	
	public void assignRole(Long memberId, Long project_id, Role role) {
		ProjectMember pm = projectMemberRepository.findById(memberId).get();
		pm.setRole(role);
		projectMemberRepository.save(pm);
	}
	
	public ProjectMember createMember(Long userId, Long projectId) {
		User user = userService.getUser(userId);
		Project project = projectService.getById(projectId);
				
		ProjectMember pm = new ProjectMember(Role.OBSERVER, user);
		pm.getProjects().add(project);
		
		return projectMemberRepository.save(pm);
	}
	
	public ProjectMember getMemberByProjectIdAndProjectMemberId(Long pId, Long pmId) {
		return projectMemberRepository.getByProjectIdAndProjectMemberId(pId, pmId);
	}
	
	public Set<ProjectMemberDTO>  getMembersByProjectId(Long pId) {
		return projectMemberRepository.getByProjectId(pId);
	}
	
	

}
