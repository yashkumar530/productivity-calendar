package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.DayName;
import cs3500.pa05.model.TaskJson;
import org.junit.jupiter.api.Test;



class TaskJsonTest {

  @Test
  void testGetters() {
    String name = "Homework";
    String description = "Complete assignment";
    DayName dayOfWeek = DayName.TUESDAY;
    boolean complete = false;

    TaskJson taskJson = new TaskJson(name, description, dayOfWeek, complete);

    assertEquals(name, taskJson.name());
    assertEquals(description, taskJson.description());
    assertEquals(dayOfWeek, taskJson.dayOfWeek());
    assertEquals(complete, taskJson.complete());
  }
}
