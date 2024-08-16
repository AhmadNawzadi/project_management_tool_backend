package com.bluewater.project_management_tool.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bluewater.project_management_tool.service.InvitationService;
import com.bluewater.project_management_tool.service.ProjectMemberService;
import com.bluewater.project_management_tool.service.ProjectService;
import com.bluewater.project_management_tool.service.TaskAssignmentService;
import com.bluewater.project_management_tool.service.TaskHistoryService;
import com.bluewater.project_management_tool.service.TaskService;
import com.bluewater.project_management_tool.service.UserService;


@WebMvcTest
public class InvitationControllerTest {
	
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
    
    @InjectMocks
    private InvitationController invitationController;
    

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invitationController).build();
    }

    @Test
    public void sendEmailTest() throws Exception {
        String to = "test@example.com";
        Long projectId = 1L;

        mockMvc.perform(post("/invitation")
                .param("to", to)
                .param("projectId", String.valueOf(projectId)))
                .andExpect(status().isOk());
        System.out.println("TOOOOOO"+ to);
        System.out.println("project id"+ projectId);
        verify(invitationService, times(1)).inviteMember(to, projectId);
    }

}
