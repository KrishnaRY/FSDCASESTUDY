package com.workout.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workout.controller.ProjectRestController;
import com.workout.model.Project;
import com.workout.repository.ProjectRepository;
import com.workout.service.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectRestController.class)
public class ProjectRestControllerTest {
	 @Autowired private MockMvc mockMvc;

	  @Autowired private ObjectMapper objectMapper;

	  @MockBean private ProjectService projectService;

	  @MockBean private ProjectRepository projectRepository;

	  private JacksonTester<Project> projectTester;

	  private Project project;
	  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(("dd/MM/yyyy"));

	  private static final String PROJECT= "Project";
	  private static final String START_DATE_STRING = "01/12/2020";
	  private static final String END_DATE_STRING = "02/12/2020";
	  private static final Date DATE_OF_START = parseDate(START_DATE_STRING);
	  private static final Date DATE_OF_END = parseDate(END_DATE_STRING);
	  private static final int PRIORITY = 1;
	  private static final int USER_ID = 2;
	  @Before
	  public void setup() {
	    JacksonTester.initFields(this, objectMapper);
	    project = new Project();
	  }

	  @Test
	  public void persistProject_IsValid_ProjectPersisted() throws Exception {
	    final String projectDTOJson = projectTester.write(project).getJson();
	    given(projectService.isValid(any(Project.class))).willReturn(true);
	    mockMvc
	        .perform(post("/addProject").content(projectDTOJson).contentType(APPLICATION_JSON_UTF8))
	        .andExpect(status().isCreated());
	    verify(projectRepository).persist(any(Project.class));
	  }
	  
	  
	  
	  
	  @Test
	  public void updateProject() throws Exception {
	    final String projectJson = projectTester.write(project).getJson();
	       mockMvc
	        .perform(post("/updateProject").content(projectJson).contentType(APPLICATION_JSON_UTF8))
	        .andExpect(status().isOk());
	    verify(projectRepository).updateProject(any(Project.class));
	  }
	  @Test
	  public void test_get_all_Projects() throws Exception {
	      List<Project> projects = Arrays.asList(
	              new Project(1, "project1",DATE_OF_START,DATE_OF_END,1,1),
	              new Project(2, "project2",DATE_OF_START,DATE_OF_END,2,2)
	             );
	      
	      final String projectJson = projectTester.write(project).getJson();
	      
	      when(projectRepository.getAllProjects()).thenReturn(projects);

	      mockMvc.perform(get("/projects").content(projectJson).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	              .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].project_ID", is(1)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].project", is("project1")))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].priority", is(1)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].user_ID", is(1)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].project_ID", is(2)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].project", is("project2")))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].priority", is(2)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].user_ID", is(2)));

	      verify(projectRepository, times(1)).getAllProjects();
	   
	  }
	  private static Date parseDate(final String dateString) {
		    try {
		      return simpleDateFormat.parse(dateString);
		    } catch (final ParseException e) {
		      return new Date();
		    }
		  }	  	  

	/*  @Test
	  public void persistProject_IsNotValid_ProjectNotPersisted() throws Exception {
	    final String projectDTOJson = projectTester.write(project).getJson();
	    given(projectService.isValid(any(Project.class))).willReturn(false);
	    mockMvc
	        .perform(post("/addProject").content(projectDTOJson).contentType(APPLICATION_JSON_UTF8))
	        .andExpect(status().isIAmATeapot());
	    verify(projectRepository, times(0)).persist(any(Project.class));
	  }*/
}
