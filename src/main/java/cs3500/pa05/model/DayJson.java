package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a day in the schedule.
 *
 * @param name   the name of the day
 * @param events the events of the day
 * @param tasks  the tasks of the day
 */
public record DayJson(
    @JsonProperty("name") DayName name,
    @JsonProperty("events") List<EventJson> events,
    @JsonProperty("tasks") List<TaskJson> tasks
) {
}
