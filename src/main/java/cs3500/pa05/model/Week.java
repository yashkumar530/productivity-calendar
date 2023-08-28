package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a week in the schedule.
 */
public class Week implements Persistable {

  private String name;
  private List<Event> events;
  private List<Task> tasks;
  private List<Day> days = new ArrayList<>();
  private int maxTasks;
  private int maxEvents;
  private int numEvents;
  private int numTasks;
  private double percentTasksComplete;

  /**
   * Gets the number of events in the week.
   *
   * @return the number of events in the week
   */
  public int getNumEvents() {
    return this.numEvents;
  }

  /**
   * Gets the number of tasks in the week.
   *
   * @return the number of tasks in the week
   */
  public int getNumTasks() {
    return this.numTasks;
  }

  /**
   * Gets the percentage of tasks completed in the week.
   *
   * @return the percentage of tasks completed in the week
   */
  public double getPercentTasksComplete() {
    return this.percentTasksComplete;
  }

  /**
   * Constructs a week
   */
  public Week() {
    for (DayName dayName : new DayName[] {DayName.SUNDAY, DayName.MONDAY, DayName.TUESDAY,
        DayName.WEDNESDAY, DayName.THURSDAY, DayName.FRIDAY, DayName.SATURDAY}) {
      days.add(new Day(dayName));
      maxTasks = 100;
      maxEvents = 100;
      events = new ArrayList<>();
      tasks = new ArrayList<>();
    }
  }

  /**
   * Saves the current information into a file named schedule.bujo
   *
   * @param pathName the path name to the file
   */
  @Override
  public void save(String pathName) {
    List<EventJson> eventsToJson = new ArrayList<>();
    List<TaskJson> tasksToJson = new ArrayList<>();
    List<DayJson> daysToJson = new ArrayList<>();
    for (Event event : events) {
      eventsToJson.add(new EventJson(event.getName(), event.getDescription(), event.getDayOfWeek(),
          event.getStartTime(), event.getDuration()));
    }
    for (Task task : tasks) {
      tasksToJson.add(new TaskJson(task.getName(), task.getDescription(),
          task.getDayOfWeek(), task.isComplete()));
    }

    for (Day day : days) {
      List<EventJson> eventsInDay = new ArrayList<>();
      List<TaskJson> tasksInDay = new ArrayList<>();
      for (Event e : day.getEvents()) {
        eventsInDay.add(new EventJson(e.getName(), e.getDescription(), e.getDayOfWeek(),
            e.getStartTime(), e.getDuration()));
      }
      for (Task t : day.getTasks()) {
        tasksInDay.add(new TaskJson(t.getName(), t.getDescription(), t.getDayOfWeek(),
            t.isComplete()));
      }
      daysToJson.add(new DayJson(day.getName(), eventsInDay, tasksInDay));
    }

    Schedule schedule = new Schedule(name, eventsToJson, tasksToJson, daysToJson, maxTasks,
        maxEvents, numEvents, numTasks, percentTasksComplete);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode save = mapper.convertValue(schedule, JsonNode.class);
    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(
          new File(pathName + "/schedule.bujo"), save);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Loads the information from a file named schedule.bujo
   *
   * @param pathName the path name to the file
   */
  @Override
  public void load(String pathName) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode jsonInput = mapper.readTree(new File(pathName));
      Schedule schedule = mapper.treeToValue(jsonInput, Schedule.class);
      this.name = schedule.name();
      this.maxTasks = schedule.maxTasks();
      this.maxEvents = schedule.maxEvents();
      this.numEvents = schedule.numEvents();
      this.numTasks = schedule.numTasks();
      this.percentTasksComplete = schedule.percentTasksComplete();
      for (EventJson eventJson : schedule.events()) {
        Event event = new Event(eventJson.name(), eventJson.description(), eventJson.dayOfWeek(),
            eventJson.startTime(), eventJson.duration());
        this.events.add(event);
      }

      for (TaskJson taskJson : schedule.tasks()) {
        Task task = new Task(taskJson.name(), taskJson.description(), taskJson.dayOfWeek());
        if (taskJson.complete()) {
          task.setComplete();
        }
        this.tasks.add(task);
      }

      for (DayJson dayJson : schedule.days()) {
        Day day = new Day(dayJson.name());
        for (EventJson eventJson : dayJson.events()) {
          Event event = new Event(eventJson.name(), eventJson.description(), eventJson.dayOfWeek(),
              eventJson.startTime(), eventJson.duration());
          day.addEvent(event);
        }

        for (TaskJson taskJson : dayJson.tasks()) {
          Task task = new Task(taskJson.name(), taskJson.description(), taskJson.dayOfWeek());
          if (taskJson.complete()) {
            task.setComplete();
          }
          day.addTask(task);
        }

        this.days.add(day);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the name of the week.
   *
   * @param name the name of the week
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the maximum number of tasks in the week.
   *
   * @param maxTasks the maximum number of tasks in the week
   */
  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  /**
   * Gets the maximum number of events in the week.
   *
   * @param maxEvents the maximum number of events in the week
   */
  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  /**
   * Updates the tasks in the schedule
   */
  public void updateTasks() {
    for (Day day : days) {
      for (Task task : day.getTasks()) {
        if (!this.tasks.contains(task)) {
          tasks.add(task);
        }
      }
    }
  }

  /**
   * Adds an event to a day
   *
   * @param day   the day to add the event to
   * @param event the event to add
   */
  public void addDayEvent(DayName day, Event event) {
    for (Day d : days) {
      if (d.getName() == (day)) {
        d.addEvent(event);
        numEvents++;
        this.events.add(event);
      }
      System.out.println("Number of events: " + numEvents);
    }
  }

  /**
   * Adds a task to a day
   *
   * @param day  the day to add the task to
   * @param task the task to add
   */
  public void addDayTask(DayName day, Task task) {
    for (Day d : days) {
      if (d.getName().equals(day)) {
        d.addTask(task);
        numTasks++;
        this.tasks.add(task);
      }
    }
    updatePercentTasksComplete();
  }

  /**
   * Checks if the maximum number of events for a day has been reached
   *
   * @param day the day to check
   * @return true if the maximum number of events has been reached, false otherwise
   */
  //TODO: also update when removing and marking complete
  public boolean isEventsFull(DayName day) {
    for (Day d : days) {
      if (d.getName().equals(day)) {
        if (d.getEvents().size() >= maxEvents) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if the maximum number of tasks for a day has been reached
   *
   * @param day the day to check
   * @return true if the maximum number of tasks has been reached, false otherwise
   */
  public boolean isTasksFull(DayName day) {
    for (Day d : days) {
      if (d.getName().equals(day)) {
        if (d.getTasks().size() >= maxTasks) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Updates the percent of tasks that are complete
   */
  public void updatePercentTasksComplete() {
    int numComplete = 0;
    for (Task task : tasks) {
      if (task.isComplete()) {
        numComplete++;
      }
    }
    percentTasksComplete = (double) numComplete / (double) tasks.size();
  }

  /**
   * Gets the tasks for the week
   *
   * @return the tasks for the week
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Gets the events for the week
   *
   * @return the events for the week
   */
  public List<Event> getEvents() {
    return this.events;
  }

  /**
   * Removes a task from a day
   *
   * @param day  the day to remove the task from
   * @param task the task to remove
   */
  public void removeDayTask(DayName day, Task task) {
    for (Day d : days) {
      if (d.getName().equals(day)) {
        d.removeTask(task);
        numTasks--;
        this.tasks.remove(task);
      }
    }
    updatePercentTasksComplete();
  }

  /**
   * Marks a task as complete
   *
   * @param day  the day the task is on
   * @param task the task to mark as complete
   */
  public void completeDayTask(DayName day, Task task) {
    for (Day d : days) {
      if (d.getName().equals(day)) {
        d.completeTask(task);
      }
    }
    updatePercentTasksComplete();
  }

  /**
   * Removes an event from a day
   *
   * @param day   the day to remove the event from
   * @param event the event to remove
   */
  public void removeDayEvent(DayName day, Event event) {
    for (Day d : days) {
      if (d.getName().equals(day)) {
        d.removeEvent(event);
        numEvents--;
        this.events.remove(event);
      }
    }
    updatePercentTasksComplete();
  }

  public String getName() {
    return name;
  }

  public int getMaxTasks() {
    return maxTasks;
  }

  public int getMaxEvents() {
    return maxEvents;
  }
}
