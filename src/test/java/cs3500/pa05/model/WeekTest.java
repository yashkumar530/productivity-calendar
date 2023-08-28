package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class WeekTest {
  private Week week;

  @BeforeEach
  void setup() {
    week = new Week();
  }

  @Test
  void testGetNumEvents() {
    assertEquals(0, week.getNumEvents());
  }

  @Test
  void testGetNumTasks() {
    assertEquals(0, week.getNumTasks());
  }

  @Test
  void testGetPercentTasksComplete() {
    assertEquals(0.0, week.getPercentTasksComplete());
  }

  @Test
  void testSetName() {
    week.setName("Week 1");
    assertEquals("Week 1", week.getName());
  }

  @Test
  void testSetMaxTasks() {
    week.setMaxTasks(50);
    assertEquals(50, week.getMaxTasks());
  }

  @Test
  void testSetMaxEvents() {
    week.setMaxEvents(20);
    assertEquals(20, week.getMaxEvents());
  }

  @Test
  void testAddDayEvent() {
    DayName day = DayName.MONDAY;
    Event event = new Event("Event 1", "Meeting", day, "09:00", 60);
    week.addDayEvent(day, event);
    assertEquals(1, week.getNumEvents());
    assertEquals(1, week.getEvents().size());
    assertEquals(event, week.getEvents().get(0));
  }

  @Test
  void testAddDayTask() {
    DayName day = DayName.TUESDAY;
    Task task = new Task("Task 1", "Do something", day);
    week.addDayTask(day, task);
    assertEquals(1, week.getNumTasks());
    assertEquals(1, week.getTasks().size());
    assertEquals(task, week.getTasks().get(0));
  }

  @Test
  void testRemoveDayTask() {
    DayName day = DayName.WEDNESDAY;
    Task task = new Task("Task 1", "Do something", day);
    week.addDayTask(day, task);
    week.removeDayTask(day, task);
    assertEquals(0, week.getNumTasks());
    assertEquals(0, week.getTasks().size());
  }

  @Test
  void testCompleteDayTask() {
    DayName day = DayName.THURSDAY;
    Task task = new Task("Task 1", "Do something", day);
    week.addDayTask(day, task);
    week.completeDayTask(day, task);
    assertTrue(task.isComplete());
    assertEquals(1.0, week.getPercentTasksComplete());
  }

  @Test
  void testRemoveDayEvent() {
    DayName day = DayName.FRIDAY;
    Event event = new Event("Event 1", "Meeting", day, "09:00", 60);
    week.addDayEvent(day, event);
    week.removeDayEvent(day, event);
    assertEquals(0, week.getNumEvents());
    assertEquals(0, week.getEvents().size());
  }

  @Test
  void testIsEventsFull() {
    DayName day = DayName.SATURDAY;
    assertFalse(week.isEventsFull(day));
    for (int i = 0; i < week.getMaxEvents(); i++) {
      Event event = new Event("Event " + (i + 1), "Meeting", day, "09:00", 60);
      week.addDayEvent(day, event);
    }
    assertTrue(week.isEventsFull(day));
  }

  @Test
  void testIsTasksFull() {
    DayName day = DayName.SUNDAY;
    assertFalse(week.isTasksFull(day));
    for (int i = 0; i < week.getMaxTasks(); i++) {
      Task task = new Task("Task " + (i + 1), "Do something", day);
      week.addDayTask(day, task);
    }
    assertTrue(week.isTasksFull(day));
  }

}
