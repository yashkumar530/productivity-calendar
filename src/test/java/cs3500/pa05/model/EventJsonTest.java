package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class EventJsonTest {

  @Test
  void testGetters() {
    String name = "Meeting";
    String description = "Discuss project progress";
    DayName dayOfWeek = DayName.MONDAY;
    String startTime = "09:00";
    int duration = 45;

    EventJson eventJson = new EventJson(name, description, dayOfWeek, startTime, duration);

    assertEquals(name, eventJson.name());
    assertEquals(description, eventJson.description());
    assertEquals(dayOfWeek, eventJson.dayOfWeek());
    assertEquals(startTime, eventJson.startTime());
    assertEquals(duration, eventJson.duration());
  }
}
