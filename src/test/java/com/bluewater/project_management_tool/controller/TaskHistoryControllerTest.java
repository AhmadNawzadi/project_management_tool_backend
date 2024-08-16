package com.bluewater.project_management_tool.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bluewater.project_management_tool.model.TaskHistory;
import com.bluewater.project_management_tool.service.InvitationService;
import com.bluewater.project_management_tool.service.ProjectMemberService;
import com.bluewater.project_management_tool.service.ProjectService;
import com.bluewater.project_management_tool.service.TaskAssignmentService;
import com.bluewater.project_management_tool.service.TaskHistoryService;
import com.bluewater.project_management_tool.service.TaskService;
import com.bluewater.project_management_tool.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class TaskHistoryControllerTest {
	
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
    

    @Autowired
    private ObjectMapper objectMapper;

    private TaskHistory taskHistory;

    @BeforeEach
    public void setup() {
        taskHistory = new TaskHistory();
        taskHistory.setId(1L);
        taskHistory.setChangeDescription("Test Description");

        when(taskHistoryService.createTaskHistory(anyLong(), anyLong(), anyString())).thenReturn(taskHistory);
        when(taskHistoryService.getHistoryById(anyLong())).thenReturn(taskHistory);
    }

    @Test
    public void testCreateTaskHistory() throws Exception {
        String description = "Test Description";

        mockMvc.perform(post("/history/1/1")
                .contentType(MediaType.TEXT_PLAIN)
                .content(objectMapper.writeValueAsString(description)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetHistoryById() throws Exception {
        mockMvc.perform(get("/history/1"))
                .andExpect(status().isOk());
    }

}
