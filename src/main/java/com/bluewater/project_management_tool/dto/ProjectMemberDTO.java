package com.bluewater.project_management_tool.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.bluewater.project_management_tool.constant.Role;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProjectMemberDTO {
	
	private Long memberId;
	private Role role;
    private String username;
    private LocalDateTime createdAt;
    
	public ProjectMemberDTO(Long memberId, Role role, String username, LocalDateTime createdAt) {
		this.role = role;
		this.username = username;
		this.createdAt = createdAt;
		this.memberId = memberId;
	}
    
    

}
