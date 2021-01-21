package shouty;

import java.time.Duration;
import java.time.Instant;

public class Shout {
    private final String shouter;
    private final Coordinate coordinate;
    private final String message;
    private final Instant shoutTime;
    private final Duration duration;

    public Shout(String shouter, Coordinate coordinate, String message, Instant shoutTime, Duration duration) {
        this.shouter = shouter;
        this.coordinate = coordinate;
        this.message = message;
        this.shoutTime = shoutTime;
        this.duration = duration;
    }

    public String getShouter() {
        return shouter;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getMessage() {
        return message;
    }

    public Duration getTimeout() {
        return duration;
    }

    public Instant getTimeoutTime() {
        return shoutTime.plus(duration);
    }
}
