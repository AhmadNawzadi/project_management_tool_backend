package com.bluewater.project_management_tool.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import org.springframework.http.MediaType;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bluewater.project_management_tool.constant.Status;
import com.bluewater.project_management_tool.model.Task;
import com.bluewater.project_management_tool.service.InvitationService;
import com.bluewater.project_management_tool.service.ProjectMemberService;
import com.bluewater.project_management_tool.service.ProjectService;
import com.bluewater.project_management_tool.service.TaskAssignmentService;
import com.bluewater.project_management_tool.service.TaskHistoryService;
import com.bluewater.project_management_tool.service.TaskService;
import com.bluewater.project_management_tool.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class TaskControllerTest {

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
    private TaskService taskService;
    @MockBean
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;

    private Task task;
    private Set<Task> tasks;

    @BeforeEach
    public void setup() {
        task = new Task();
        task.setId(1L);
        task.setName("Test Task");

        tasks = new HashSet<>();
        tasks.add(task);

        doNothing().when(taskService).createTask(any(Task.class), anyLong());
        when(taskService.getTaskById(anyLong())).thenReturn(task);
        when(taskService.getTasksByProject(anyLong())).thenReturn(tasks);
        doNothing().when(taskService).updateTask(anyLong(), any(Task.class));
        when(taskService.getTasksByStatus(any(Status.class))).thenReturn(tasks);
    }

    @Test
    public void testCreateTask() throws Exception {
        mockMvc.perform(post("/task/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Task"));
    }

    @Test
    public void testGetTaskById() throws Exception {
        mockMvc.perform(get("/task/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTasksByProjectId() throws Exception {
        mockMvc.perform(get("/task")
                .param("projectId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        mockMvc.perform(put("/task/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTasksByStatus() throws Exception {
        mockMvc.perform(get("/task/status")
                .param("status", "TODO"))
                .andExpect(status().isOk());
    }
}
