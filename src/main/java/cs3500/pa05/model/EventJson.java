package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an event in the schedule(serialized for Json)
 *
 * @param name        the name of the event
 * @param description the description of the event
 * @param dayOfWeek   the day of the week the event is on
 * @param startTime   the start time of the event
 * @param duration    the duration of the event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("dayOfWeek") DayName dayOfWeek,
    @JsonProperty("startTime") String startTime,
    @JsonProperty("duration") int duration
) {
}
