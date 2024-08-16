package com.bluewater.project_management_tool.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bluewater.project_management_tool.service.InvitationService;
import com.bluewater.project_management_tool.service.ProjectMemberService;
import com.bluewater.project_management_tool.service.ProjectService;
import com.bluewater.project_management_tool.service.TaskAssignmentService;
import com.bluewater.project_management_tool.service.TaskHistoryService;
import com.bluewater.project_management_tool.service.TaskService;
import com.bluewater.project_management_tool.service.UserService;

import jakarta.mail.MessagingException;

@WebMvcTest
public class TaskAssignmentControllerTest {
	
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
    
    @BeforeEach
    public void setup() throws MessagingException, IOException {
        doNothing().when(taskAssignmentService).assignTaskToMember(anyLong(), anyLong());
    }

    @Test
    public void testAssignTaskToMember() throws Exception {
        mockMvc.perform(post("/assignment/1/1"))
                .andExpect(status().isCreated());
    }
    
    

}
