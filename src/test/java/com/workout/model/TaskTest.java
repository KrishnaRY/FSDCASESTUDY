package com.workout.model;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.workout.model.Task;

@JsonTest
@RunWith(SpringRunner.class)
public class TaskTest {

  @Autowired private JacksonTester<Task> json;

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
 

  private static final String JSON_TO_DESERIALIZE =
      "{\"task\":\""
          + TASK
          + "\",\"start_Date\":\""
          + START_DATE_STRING
          + "\",\"end_Date\":\""
          + END_DATE_STRING
          + "\",\"status\":\""
          + STATUS
          + "\",\"priority\":"
          + PRIORITY
          +",\"user_ID\":"
          + USER_ID
          +",\"parent_ID\":"
          + PARENT_ID
          +",\"project_ID\":"
          + PROJCT_ID
          +"}";

  private Task task;

  private static Date parseDate(final String dateString) {
    try {
      return simpleDateFormat.parse(dateString);
    } catch (final ParseException e) {
      return new Date();
    }
  }

  @Before
  public void setup() throws ParseException {
	  task = new Task( PARENT_ID,  PROJCT_ID,  TASK,  DATE_OF_START,  DATE_OF_END,  PRIORITY,  STATUS, USER_ID);
  }

  @Test
  public void taskSerializes() throws IOException {
    assertThat(this.json.write(task))
        .extractingJsonPathStringValue("@.task")
        .isEqualTo(TASK);
  }

  @Test
  public void prioritySerializes() throws IOException {
    assertThat(this.json.write(task))
        .extractingJsonPathNumberValue("@.priority")
        .isEqualTo(PRIORITY);
  }

  @Test
  public void user_IDSerializes() throws IOException {
    assertThat(this.json.write(task))
        .extractingJsonPathNumberValue("@.user_ID")
        .isEqualTo(USER_ID);
  }

  @Test
  public void project_IDSerializes() throws IOException {
    assertThat(this.json.write(task))
        .extractingJsonPathNumberValue("@.project_ID")
        .isEqualTo(PROJCT_ID);
  }
  @Test
  public void parent_IDSerializes() throws IOException {
    assertThat(this.json.write(task))
        .extractingJsonPathNumberValue("@.parent_ID")
        .isEqualTo(PARENT_ID);
  }

  @Test
  public void taskDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getTask()).isEqualTo(TASK);
  }

  @Test
  public void priorityDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getPriority()).isEqualTo(PRIORITY);
  }

  @Test
  public void statusDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getStatus()).isEqualTo(STATUS);
  }
  
  @Test
  public void user_IDDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getUser_ID()).isEqualTo(USER_ID);
  }
  
  @Test
  public void project_IDDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getProject_ID()).isEqualTo(PROJCT_ID);
  }
  
  @Test
  public void parent_IDDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getParent_ID()).isEqualTo(PARENT_ID);
  }
}

