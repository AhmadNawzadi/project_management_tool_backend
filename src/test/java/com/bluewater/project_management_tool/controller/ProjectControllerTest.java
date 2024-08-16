package com.bluewater.project_management_tool.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import org.springframework.http.MediaType;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bluewater.project_management_tool.model.Project;
import com.bluewater.project_management_tool.service.InvitationService;
import com.bluewater.project_management_tool.service.ProjectMemberService;
import com.bluewater.project_management_tool.service.ProjectService;
import com.bluewater.project_management_tool.service.TaskAssignmentService;
import com.bluewater.project_management_tool.service.TaskHistoryService;
import com.bluewater.project_management_tool.service.TaskService;
import com.bluewater.project_management_tool.service.UserService;

@WebMvcTest
public class ProjectControllerTest {
	
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


    private Project project1;
    private Project project2;

    @BeforeEach
    public void setup() {
        project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");
        
        project2 = new Project();
        project2.setId(2L);
        project2.setName("Project 2");
    }

    @Test
    public void testCreateProject() throws Exception {
    	when(projectService.createProject(any(Project.class))).thenReturn(project1);

        mockMvc.perform(post("/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"Project 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Project 1"));
    }

    @Test
    public void testGetAllProjects() throws Exception {
        when(projectService.getAllProjects()).thenReturn(Arrays.asList(project1, project2));
        mockMvc.perform(get("/project")).andExpect(status().isOk());
    }
	
	

}
