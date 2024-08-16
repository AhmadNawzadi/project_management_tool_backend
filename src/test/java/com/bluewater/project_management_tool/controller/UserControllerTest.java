package com.bluewater.project_management_tool.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bluewater.project_management_tool.model.User;
import com.bluewater.project_management_tool.service.InvitationService;
import com.bluewater.project_management_tool.service.ProjectMemberService;
import com.bluewater.project_management_tool.service.ProjectService;
import com.bluewater.project_management_tool.service.TaskAssignmentService;
import com.bluewater.project_management_tool.service.TaskHistoryService;
import com.bluewater.project_management_tool.service.TaskService;
import com.bluewater.project_management_tool.service.UserService;

@WebMvcTest
public class UserControllerTest {
	
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
    
    private User user;
    
    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    public void setUp() {
    	MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        
    	user = new User();
    	//user.setId(1L);
    	user.setUsername("ThomasJohn");
    	user.setEmail("john@gmail.com");
    	user.setPassword("1234");
    }
    
    @Test
    public void createUserTest() throws Exception {
    	when(userService.createUser(any(User.class))).thenReturn(user);
    	
    	 mockMvc.perform(post("/user/create")
    	          .contentType(MediaType.APPLICATION_JSON)
                  .content("{\"username\": \"ThomasJohn\" , \"email\": \"john@gmail.com\", \"password\": \"1234\"}"))
    				//.andExpect(jsonPath("$.id").value(1))
                  .andExpect(jsonPath("$.username").value("ThomasJohn"))
                  .andExpect(jsonPath("$.email").value("john@gmail.com"))
                  .andExpect(jsonPath("$.password").value("1234"));
    	
   
    }

}
