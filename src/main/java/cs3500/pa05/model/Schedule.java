package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


/**
 * Represents a schedule(serialized for Json)
 *
 * @param name                 the name of the schedule
 * @param events               the events of the schedule
 * @param tasks                the tasks of the schedule
 * @param days                 the days of the schedule
 * @param maxTasks             the maximum number of tasks allowed in the schedule
 * @param maxEvents            the maximum number of events allowed in the schedule
 * @param numEvents            the number of events in the schedule
 * @param numTasks             the number of tasks in the schedule
 * @param percentTasksComplete the percentage of tasks completed in the schedule
 */
public record Schedule(
    @JsonProperty("name") String name,
    @JsonProperty("events") List<EventJson> events,
    @JsonProperty("tasks") List<TaskJson> tasks,
    @JsonProperty("days") List<DayJson> days,
    @JsonProperty("maxTasks") int maxTasks,
    @JsonProperty("maxEvents") int maxEvents,
    @JsonProperty("numEvents") int numEvents,
    @JsonProperty("numTasks") int numTasks,
    @JsonProperty("percentTasksComplete") double percentTasksComplete

) {

}
