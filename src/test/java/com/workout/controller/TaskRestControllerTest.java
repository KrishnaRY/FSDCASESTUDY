package com.workout.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
import com.workout.controller.TaskRestController;
import com.workout.model.Task;
import com.workout.repository.TaskRepository;
import com.workout.service.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskRestController.class)
public class TaskRestControllerTest {
	 @Autowired private MockMvc mockMvc;

	  @Autowired private ObjectMapper objectMapper;

	  @MockBean private TaskService taskctService;

	  @MockBean private TaskRepository taskRepository;

	  private JacksonTester<Task> taskTester;

	  private Task task;
	  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(("dd/MM/yyyy"));

	  private static final String TASK= "task";
	  private static final int PARENT_ID=0;
	  private static final int PROJCT_ID=1;
	  private static final String START_DATE_STRING = "01/12/2020";
	  private static final String END_DATE_STRING = "02/12/2020";
	  private static final Date DATE_OF_START = parseDate(START_DATE_STRING);
	  private static final Date DATE_OF_END = parseDate(END_DATE_STRING);
	  private static final int PRIORITY = 1;
	  private static final int USER_ID = 2;
	  private static final String  STATUS="start";
	  @Before
	  public void setup() {
	    JacksonTester.initFields(this, objectMapper);
	    task = new Task();
	  }

	  @Test
	  public void persistTask_IsValid_TaskPersisted() throws Exception {
	    final String taskJson = taskTester.write(task).getJson();
	 //   given(taskctService.isValid(any(Project.class))).willReturn(true);
	    mockMvc
	        .perform(post("/addTask").content(taskJson).contentType(APPLICATION_JSON_UTF8))
	        .andExpect(status().isCreated());
	    verify(taskRepository).persist(any(Task.class));
	  }
	  
	  
	  
	  
	  @Test
	  public void updateTask() throws Exception {
	    final String taskJson = taskTester.write(task).getJson();
	       mockMvc
	        .perform(post("/updateTask").content(taskJson).contentType(APPLICATION_JSON_UTF8))
	        .andExpect(status().isOk());
	    verify(taskRepository).updateTask(any(Task.class));
	  }
	  @Test
	  public void test_get_all_Tasks() throws Exception {
	      List<Task> tasks = Arrays.asList(
	    		  new Task(1, PARENT_ID,  PROJCT_ID,  TASK,  DATE_OF_START,  DATE_OF_END,  3,  "sus", USER_ID),
	    		  new Task(2,6,  5,  "tt",  DATE_OF_START,  DATE_OF_END,  PRIORITY,  STATUS, 2)
	             );
	      
	      final String projectJson = taskTester.write(task).getJson();
	      
	      when(taskRepository.getAllTasks()).thenReturn(tasks);

	      mockMvc.perform(get("/tasks").content(projectJson).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	              .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].task_ID", is(1)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].task", is(TASK)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].priority", is(3)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].user_ID", is(2)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].project_ID", is(1)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[0].parent_ID", is(0)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].task_ID", is(2)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].task", is("tt")))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].priority", is(1)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].project_ID", is(5)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].parent_ID", is(6)))
	              .andExpect(MockMvcResultMatchers.jsonPath("$[1].user_ID", is(2)));

	      verify(taskRepository, times(1)).getAllTasks();
	   
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
