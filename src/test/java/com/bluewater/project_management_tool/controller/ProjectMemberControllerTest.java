package com.bluewater.project_management_tool.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bluewater.project_management_tool.constant.Role;
import com.bluewater.project_management_tool.dto.ProjectMemberDTO;
import com.bluewater.project_management_tool.model.ProjectMember;
import com.bluewater.project_management_tool.model.User;
import com.bluewater.project_management_tool.service.InvitationService;
import com.bluewater.project_management_tool.service.ProjectMemberService;
import com.bluewater.project_management_tool.service.ProjectService;
import com.bluewater.project_management_tool.service.TaskAssignmentService;
import com.bluewater.project_management_tool.service.TaskHistoryService;
import com.bluewater.project_management_tool.service.TaskService;
import com.bluewater.project_management_tool.service.UserService;

@WebMvcTest
public class ProjectMemberControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;
    @MockBean
    private InvitationService invitationService;
    @MockBean
    private ProjectMemberService projectMemberService;
    @MockBean
    private TaskAssignmentService taskAssignmentService;
    @MockBean
    private TaskHistoryService taskHistoryService;
    @MockBean
    private UserService userService;
    @MockBean
    private TaskService taskService;

    private ProjectMember projectMember;
    private Set<ProjectMemberDTO> projectMembers;


    
    @BeforeEach
    public void setup() {
        projectMember = new ProjectMember();
        User user = new User();
        projectMember.setId(1L);
        projectMember.setUser(user); 
        projectMember.setProjects(null);
        projectMember.setRole(Role.MEMBER);

        ProjectMemberDTO dto = new ProjectMemberDTO();
        dto.setCreatedAt(LocalDateTime.now());
        dto.setUsername("ahmad");
        dto.setRole(Role.MEMBER);

        projectMembers = new HashSet<>();
        projectMembers.add(dto);
    }
    

    @Test
    public void testAssignRole() throws Exception {
        doNothing().when(projectMemberService).assignRole(anyLong(), anyLong(), any(Role.class));

        mockMvc.perform(patch("/members/1/1/MEMBER"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMemberByProjectIdAndProjectMemberId() throws Exception {
        when(projectMemberService.getMemberByProjectIdAndProjectMemberId(anyLong(), anyLong())).thenReturn(projectMember);

        mockMvc.perform(get("/members/1/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMembersByProjectId() throws Exception {
        when(projectMemberService.getMembersByProjectId(anyLong())).thenReturn(projectMembers);

        mockMvc.perform(get("/members/1"))
                .andExpect(status().isOk());

    }

}
