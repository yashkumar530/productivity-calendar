package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;



class ScheduleTest {

  @Test
  void testGetters() {
    String name = "My Schedule";
    int maxTasks = 100;
    int maxEvents = 100;
    int numEvents = 5;
    int numTasks = 10;
    double percentTasksComplete = 0.5;

    List<EventJson> events = new ArrayList<>();
    List<TaskJson> tasks = new ArrayList<>();
    List<DayJson> days = new ArrayList<>();

    Schedule schedule = new Schedule(
        name,
        events,
        tasks,
        days,
        maxTasks,
        maxEvents,
        numEvents,
        numTasks,
        percentTasksComplete
    );

    assertEquals(name, schedule.name());
    assertEquals(events, schedule.events());
    assertEquals(tasks, schedule.tasks());
    assertEquals(days, schedule.days());
    assertEquals(maxTasks, schedule.maxTasks());
    assertEquals(maxEvents, schedule.maxEvents());
    assertEquals(numEvents, schedule.numEvents());
    assertEquals(numTasks, schedule.numTasks());
    assertEquals(percentTasksComplete, schedule.percentTasksComplete());
  }
}
