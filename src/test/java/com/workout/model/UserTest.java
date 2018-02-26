package com.workout.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.workout.model.User;

@JsonTest
@RunWith(SpringRunner.class)
public class UserTest {

  @Autowired private JacksonTester<User> json;


  private static final String FIRST_NAME = "First name";
  private static final String LAST_NAME = "Last name";
  private static final String EMPLOYEE_ID = "Employee ID";


  private static final String JSON_TO_DESERIALIZE =
      "{\"first_Name\":\""
          + FIRST_NAME
          + "\",\"last_Name\":\""
          + LAST_NAME
          + "\",\"employee_ID\":\""
          + EMPLOYEE_ID
          + "\"}";

  private User user;



  @Before
  public void setup() throws ParseException {
	  user = new User(FIRST_NAME, LAST_NAME, EMPLOYEE_ID);
  }

  @Test
  public void firstNameSerializes() throws IOException {
    assertThat(this.json.write(user))
        .extractingJsonPathStringValue("@.first_Name")
        .isEqualTo(FIRST_NAME);
  }

  @Test
  public void lastNameSerializes() throws IOException {
    assertThat(this.json.write(user))
        .extractingJsonPathStringValue("@.last_Name")
        .isEqualTo(LAST_NAME);
  }

 

  @Test
  public void employeeidSerializes() throws IOException {
    assertThat(this.json.write(user))
        .extractingJsonPathStringValue("@.employee_ID")
        .isEqualTo(EMPLOYEE_ID);
  }

 
  @Test
  public void firstNameDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getFirst_Name()).isEqualTo(FIRST_NAME);
  }

  @Test
  public void lastNameDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getLast_Name()).isEqualTo(LAST_NAME);
  }

  @Test
  public void employeeidDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getEmployee_ID())
        .isEqualTo(EMPLOYEE_ID);
  }

 
}
