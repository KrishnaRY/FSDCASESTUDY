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

import com.workout.model.Project;

@JsonTest
@RunWith(SpringRunner.class)
public class ProjectTest {

  @Autowired private JacksonTester<Project> json;

  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(("dd/MM/yyyy"));

  private static final String PROJECT= "Project";
  private static final String START_DATE_STRING = "01/12/2020";
  private static final String END_DATE_STRING = "02/12/2020";
  private static final Date DATE_OF_START = parseDate(START_DATE_STRING);
  private static final Date DATE_OF_END = parseDate(END_DATE_STRING);
  private static final int PRIORITY = 1;
  private static final int USER_ID = 2;
 

  private static final String JSON_TO_DESERIALIZE =
      "{\"project\":\""
          + PROJECT
          + "\",\"start_Date\":\""
          + START_DATE_STRING
          + "\",\"end_Date\":\""
          + END_DATE_STRING
          + "\",\"priority\":"
          + PRIORITY
          +",\"user_ID\":"
          + USER_ID
          +"}";

  private Project project;

  private static Date parseDate(final String dateString) {
    try {
      return simpleDateFormat.parse(dateString);
    } catch (final ParseException e) {
      return new Date();
    }
  }

  @Before
  public void setup() throws ParseException {
	  project = new Project(PROJECT, DATE_OF_START, DATE_OF_END, PRIORITY,USER_ID);
  }

  @Test
  public void projectSerializes() throws IOException {
    assertThat(this.json.write(project))
        .extractingJsonPathStringValue("@.project")
        .isEqualTo(PROJECT);
  }

  @Test
  public void prioritySerializes() throws IOException {
    assertThat(this.json.write(project))
        .extractingJsonPathNumberValue("@.priority")
        .isEqualTo(PRIORITY);
  }




  public void projectDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getProject()).isEqualTo(PROJECT);
  }


  public void priorityDeserializes() throws IOException {
    assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getPriority()).isEqualTo(PRIORITY);
  }

  
}

