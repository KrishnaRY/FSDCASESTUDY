package com.workout.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workout.controller.UserRestController;
import com.workout.model.User;
import com.workout.repository.UserRepository;
import com.workout.service.UserService;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;
@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private UserService userService;

  @MockBean private UserRepository userRepository;

  private JacksonTester<User> jsonTester;

  private User user;

  @Before
  public void setup() {
    JacksonTester.initFields(this, objectMapper);
    user = new User();
  }

  @Test
  public void persistUser_IsValid_UserPersisted() throws Exception {
    final String userJson = jsonTester.write(user).getJson();
   given(userService.isValid(any(User.class))).willReturn(true);
    mockMvc
        .perform(post("/addUser").content(userJson).contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated());
    verify(userRepository).persist(any(User.class));
  }
  @Test
  public void updateUser() throws Exception {
    final String userJson = jsonTester.write(user).getJson();
       mockMvc
        .perform(post("/updateUser").content(userJson).contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
    verify(userRepository).updateUser(any(User.class));
  }
  
  @Test
  public void deleteUser() throws Exception {
     mockMvc.perform(delete("/delete/1").contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk());
    verify(userRepository).deleteUser(1);
  }
  
 
  @Test
  public void persistUser_IsNotValid_UserNotPersisted() throws Exception {
    final String userJson = jsonTester.write(user).getJson();
   given(userService.isValid(any(User.class))).willReturn(false);
    mockMvc
        .perform(post("/addUser").content(userJson).contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isBadRequest());
    verify(userRepository, times(0)).persist(any(User.class));
  }
  
  @Test
  public void test_get_all_success() throws Exception {
      List<User> users = Arrays.asList(
              new User(1, "Daenerys","Targaryen","12346"),
              new User(2, "John","Snow","12345")
             );
      
      final String userJson = jsonTester.write(user).getJson();
      
      when(userRepository.getAllUsers()).thenReturn(users);

      mockMvc.perform(get("/users").content(userJson).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
              .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].user_ID", is(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].first_Name", is("Daenerys")))
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].last_Name", is("Targaryen")))
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].employee_ID", is("12346")))
              .andExpect(MockMvcResultMatchers.jsonPath("$[1].user_ID", is(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$[1].first_Name", is("John")))
              .andExpect(MockMvcResultMatchers.jsonPath("$[1].last_Name", is("Snow")))
              .andExpect(MockMvcResultMatchers.jsonPath("$[1].employee_ID", is("12345")));

      verify(userRepository, times(1)).getAllUsers();
   
  }
  
}
