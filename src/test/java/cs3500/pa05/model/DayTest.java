package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class DayTest {
  private Day day;
  private Event event;
  private Task task;

  @BeforeEach
  void setup() {
    day = new Day(DayName.MONDAY);
    event = new Event("Meeting", "A meeting",
        DayName.FRIDAY, "12:00 PM", 35);
    task = new Task("Task 1", "A task", DayName.MONDAY);
  }

  @Test
  void testAddEvent() {
    day.addEvent(event);
    List<Event> events = day.getEvents();
    assertEquals(1, events.size());
    assertEquals(event, events.get(0));
  }

  @Test
  void testAddTask() {
    day.addTask(task);
    List<Task> tasks = day.getTasks();
    assertEquals(1, tasks.size());
    assertEquals(task, tasks.get(0));
  }

  @Test
  void testRemoveTask() {
    Task task2 = new Task("Task 2", "Another task", DayName.MONDAY);
    day.addTask(task);
    day.addTask(task2);
    day.removeTask(task);
    List<Task> tasks = day.getTasks();
    assertEquals(1, tasks.size());
    assertEquals(task2, tasks.get(0));
  }

  @Test
  void testCompleteTask() {
    day.addTask(task);
    day.completeTask(task);
    assertTrue(task.isComplete());
  }

  @Test
  void testRemoveEvent() {
    Event event2 = new Event("Lunch", "12:00 PM", DayName.FRIDAY,
        "1:00", 60);
    day.addEvent(event);
    day.addEvent(event2);
    day.removeEvent(event);
    List<Event> events = day.getEvents();
    assertEquals(1, events.size());
    assertEquals(event2, events.get(0));
  }
}
