package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a task in the schedule(serialized for Json)
 *
 * @param name        the name of the task
 * @param description the description of the task
 * @param dayOfWeek   the day of the week the task is on
 * @param complete    whether the task is complete
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("dayOfWeek") DayName dayOfWeek,
    @JsonProperty("complete") boolean complete
) {
}
