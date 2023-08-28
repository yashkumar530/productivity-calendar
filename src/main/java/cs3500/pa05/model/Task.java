package cs3500.pa05.model;

/**
 * Represents a task in the schedule.
 */
public class Task {
  private String name;
  private String description;
  private boolean complete;
  private DayName dayOfWeek;

  /**
   * Constructs a task.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param dayOfWeek   the day of the week the task is on
   */
  public Task(String name, String description, DayName dayOfWeek) {
    this.name = name;
    this.description = description;
    this.complete = false;
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Gets whether the task is complete.
   *
   * @return whether the task is complete
   */
  public boolean isComplete() {
    return this.complete;
  }

  /**
   * Sets the task to complete.
   */
  public void setComplete() {
    this.complete = true;
  }

  /**
   * Gets the name of the task.
   *
   * @return the name of the task
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the description of the task.
   *
   * @return the description of the task
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the day of the week the task is on.
   *
   * @return the day of the week the task is on
   */
  public DayName getDayOfWeek() {
    return this.dayOfWeek;
  }

  public void setDayOfWeek(DayName day) {
    this.dayOfWeek = day;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
