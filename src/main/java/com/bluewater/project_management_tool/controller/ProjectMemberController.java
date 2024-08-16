package com.bluewater.project_management_tool.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluewater.project_management_tool.constant.Role;
import com.bluewater.project_management_tool.dto.ProjectMemberDTO;
import com.bluewater.project_management_tool.model.ProjectMember;
import com.bluewater.project_management_tool.service.ProjectMemberService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/members")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectMemberController {
	
	private ProjectMemberService pms;
	

	@PatchMapping("/{memberId}/{projectId}/{role}")
	public void assignRole(@PathVariable Long memberId, @PathVariable Long projectId, @PathVariable Role role) {
		pms.assignRole(memberId, projectId, role);
	}
	
	@GetMapping("/{pId}/{pmId}")
	public ProjectMember getByProjectIdAndProjectMemberId(@PathVariable Long pId, @PathVariable Long pmId) {
		return pms.getMemberByProjectIdAndProjectMemberId(pId, pmId);
	}
	
	@GetMapping("/{pId}")
	public Set<ProjectMemberDTO> getMembersByProjectId(@PathVariable Long pId) {
		return pms.getMembersByProjectId(pId);
	}

}
