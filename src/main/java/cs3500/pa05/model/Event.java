package cs3500.pa05.model;

/**
 * Represents an event in the schedule.
 */
public class Event {
  private String name;
  private String description;
  private DayName dayOfWeek;
  private String startTime;
  private int duration;

  /**
   * Constructs an event.
   *
   * @param name        the name of the event
   * @param description the description of the event
   * @param dayOfWeek   the day of the week the event is on
   * @param startTime   the start time of the event
   * @param duration    the duration of the event
   */
  public Event(String name, String description, DayName dayOfWeek, String startTime, int duration) {
    this.name = name;
    this.description = description;
    this.dayOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.duration = duration;
  }


  /**
   * Gets the name of the event
   *
   * @return the name of the event
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the description of the event.
   *
   * @return the description of the event
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the day of the week the event is on.
   *
   * @return the day of the week the event is on
   */
  public DayName getDayOfWeek() {
    return this.dayOfWeek;
  }

  /**
   * Gets the start time of the event.
   *
   * @return the start time of the event
   */
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * Gets the duration of the event.
   *
   * @return the duration of the event
   */
  public int getDuration() {
    return this.duration;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDayOfWeek(DayName day) {
    this.dayOfWeek = day;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }
}