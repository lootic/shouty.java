package shouty;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Shouty {
    private static final int MESSAGE_RANGE = 1000;
    private static final Duration DEFAULT_DURATION = Duration.ofMinutes(5);
    private final Map<String, Coordinate> locations = new HashMap<>();
    private final List<Shout> shouts = new ArrayList<>();

    //TODO: Fix with fancy dependency injection
    private Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public void setLocation(String person, Coordinate location) {
        locations.put(person, location);
    }

    public void shout(String shouter, String shout) {
        shout(shouter, shout, DEFAULT_DURATION);
    }

    public void shout(String shouter, String shout, Duration duration) {
        shouts.add(new Shout(shouter, locations.get(shouter), shout, clock.instant(), duration));
    }

    public List<Shout> getShoutsHeardBy(String listener) {
        return getShoutsNear(locations.get(listener))
                .stream().filter(shout -> !shout.getShouter().equals(listener))
                .collect(Collectors.toList());
    }

    public List<Shout> getShoutsNear(Coordinate coordinate) {
        return shouts.stream()
                .filter(shout -> shout.getCoordinate().distanceFrom(coordinate) < MESSAGE_RANGE)
                .filter(shout -> shout.getTimeoutTime().isAfter(clock.instant()))
                .collect(Collectors.toList());
    }
}
