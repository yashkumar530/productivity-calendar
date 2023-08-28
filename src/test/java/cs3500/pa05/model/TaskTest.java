package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TaskTest {
  private Task task;

  @BeforeEach
  void setup() {
    task = new Task("Task 1", "Do something", DayName.MONDAY);
  }

  @Test
  void testIsComplete() {
    assertFalse(task.isComplete());
  }

  @Test
  void testSetComplete() {
    task.setComplete();
    assertTrue(task.isComplete());
  }

  @Test
  void testGetName() {
    assertEquals("Task 1", task.getName());
  }

  @Test
  void testGetDescription() {
    assertEquals("Do something", task.getDescription());
  }

  @Test
  void testGetDayOfWeek() {
    assertEquals(DayName.MONDAY, task.getDayOfWeek());
  }

  @Test
  void testSetName() {
    task.setName("Updated Task");
    assertEquals("Updated Task", task.getName());
  }

  @Test
  void testSetDescription() {
    task.setDescription("Updated Description");
    assertEquals("Updated Description", task.getDescription());
  }

  @Test
  void testSetDayOfWeek() {
    task.setDayOfWeek(DayName.TUESDAY);
    assertEquals(DayName.TUESDAY, task.getDayOfWeek());
  }
}
