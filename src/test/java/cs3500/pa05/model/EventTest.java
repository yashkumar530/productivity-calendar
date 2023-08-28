package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class EventTest {
  private Event event;

  @BeforeEach
  void setup() {
    event = new Event("Meeting", "Team Meeting",
        DayName.MONDAY, "10:00 AM", 60);
  }

  @Test
  void testGetName() {
    assertEquals("Meeting", event.getName());
  }

  @Test
  void testGetDescription() {
    assertEquals("Team Meeting", event.getDescription());
  }

  @Test
  void testGetDayOfWeek() {
    assertEquals(DayName.MONDAY, event.getDayOfWeek());
  }

  @Test
  void testGetStartTime() {
    assertEquals("10:00 AM", event.getStartTime());
  }

  @Test
  void testGetDuration() {
    assertEquals(60, event.getDuration());
  }

  @Test
  void testSetName() {
    event.setName("Updated Meeting");
    assertEquals("Updated Meeting", event.getName());
  }

  @Test
  void testSetDescription() {
    event.setDescription("Updated Team Meeting");
    assertEquals("Updated Team Meeting", event.getDescription());
  }

  @Test
  void testSetDayOfWeek() {
    event.setDayOfWeek(DayName.TUESDAY);
    assertEquals(DayName.TUESDAY, event.getDayOfWeek());
  }

  @Test
  void testSetStartTime() {
    event.setStartTime("11:00 AM");
    assertEquals("11:00 AM", event.getStartTime());
  }

  @Test
  void testSetDuration() {
    event.setDuration(90);
    assertEquals(90, event.getDuration());
  }
}
