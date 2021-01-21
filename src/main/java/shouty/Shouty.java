package shouty;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Shouty {
    private static final int MESSAGE_RANGE = 1000;
    private Map<String, Coordinate> locations = new HashMap<>();
    private Map<String, List<Shout>> shouts = new HashMap<>();

    public void setLocation(String person, Coordinate location) {
        locations.put(person, location);
    }

    public void shout(String shouter, String message) {
        Shout shout = new Shout(message, locations.get(shouter));

        if (!shouts.containsKey(shouter)) {
            List<Shout> personsShouts = new ArrayList<>();
            shouts.put(shouter, personsShouts);
        }

        shouts.get(shouter).add(shout);
    }

    public Map<String, List<String>> getShoutsHeardBy(String listener) {
        HashMap<String, List<String>> shoutsHeard = new HashMap<>();

        for (Map.Entry<String, List<Shout>> entry : shouts.entrySet()) {
            String shouter = entry.getKey();
            if (!shouter.equals(listener)) {
                List<String> messagesInRange = new ArrayList<>();
                for (Shout personsShout : entry.getValue()) {
                    int distance = locations.get(listener).distanceFrom(personsShout.coordinate);
                    if (distance < MESSAGE_RANGE) {
                        messagesInRange.add(personsShout.message);
                    }
                }
                if (!messagesInRange.isEmpty()) {
                    shoutsHeard.put(shouter, messagesInRange);
                }
            }
        }
        return shoutsHeard;
    }

    private static class Shout {
        String message;
        Coordinate coordinate;

        public Shout(String message, Coordinate coordinate) {
            this.message = message;
            this.coordinate = coordinate;
        }
    }

}
