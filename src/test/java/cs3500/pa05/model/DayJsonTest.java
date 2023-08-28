package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class DayJsonTest {

  @Test
  void testGetters() {
    List<EventJson> events = new ArrayList<>();
    events.add(new EventJson("Event 1", "Meeting", DayName.MONDAY,
        "09:00", 60));
    events.add(new EventJson("Event 2", "Lunch",
        DayName.MONDAY, "12:00", 60));

    List<TaskJson> tasks = new ArrayList<>();
    tasks.add(new TaskJson("Task 1", "Do something",
        DayName.MONDAY, false));
    tasks.add(new TaskJson("Task 2", "Buy groceries",
        DayName.MONDAY, true));

    DayJson dayJson = new DayJson(DayName.MONDAY, events, tasks);

    assertEquals(DayName.MONDAY, dayJson.name());
    assertEquals(events, dayJson.events());
    assertEquals(tasks, dayJson.tasks());
  }
}
