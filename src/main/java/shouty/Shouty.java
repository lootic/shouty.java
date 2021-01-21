package shouty;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Shouty {
    private static final int MESSAGE_RANGE = 1000;
    private final Map<String, Coordinate> locations = new HashMap<>();
    private final List<Shout> shouts = new ArrayList<>();

    public void setLocation(String person, Coordinate location) {
        locations.put(person, location);
    }

    public void shout(String shouter, String shout) {
        shouts.add(new Shout(shouter, locations.get(shouter), shout));
    }

    public List<Shout> getShoutsHeardBy(String listener) {
        return getShoutsNear(locations.get(listener))
                .stream().filter(shout -> !shout.getShouter().equals(listener))
                .collect(Collectors.toList());
    }

    public List<Shout> getShoutsNear(Coordinate coordinate) {
        return shouts.stream()
                .filter(shout -> shout.getCoordinate().distanceFrom(coordinate) < MESSAGE_RANGE)
                .collect(Collectors.toList());
    }
}
