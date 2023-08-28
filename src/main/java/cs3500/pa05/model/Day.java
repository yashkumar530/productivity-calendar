package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a day in the schedule.
 */
public class Day {
  private DayName name;
  private List<Event> events;
  private List<Task> tasks;
  private int maxTasks;
  private int maxEvents;

  Day(DayName name) {
    this.name = name;
    this.events = new ArrayList<>();
    this.tasks = new ArrayList<>();
  }

  /**
   * Adds an event to the day
   *
   * @param event the event to be added
   */
  public void addEvent(Event event) {
    this.events.add(event);
  }

  /**
   * Adds a task to the day
   *
   * @param task the task to be added
   */
  public void addTask(Task task) {
    this.tasks.add(task);
  }

  /**
   * Gets the tasks of the day
   *
   * @return All the tasks for the day
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  public List<Event> getEvents() {
    return this.events;
  }

  public DayName getName() {
    return this.name;
  }

  public void removeTask(Task task) {
    this.tasks.remove(task);
  }

  public void completeTask(Task task) {
    task.setComplete();
  }

  public void removeEvent(Event event) {
    this.events.remove(event);
  }
}
