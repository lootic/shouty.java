package shouty;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Shouty {
    private static final int MESSAGE_RANGE = 1000;
    private Map<String, Coordinate> locations = new HashMap<>();
    private Map<String, List<Shout>> shouts = new HashMap<>();
    private LocalDateTime currentTime = LocalDateTime.now();

    public void setLocation(String person, Coordinate location) {
        locations.put(person, location);
    }

    public void shout(String shouter, String message) {
        Shout shout = new Shout(message, locations.get(shouter), currentTime);

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
                for (Shout shout : entry.getValue()) {
                    int distance = locations.get(listener).distanceFrom(shout.coordinate);
                    if (distance < MESSAGE_RANGE && shout.timeOfShout.plusMinutes(1).isAfter(currentTime)) {
                        messagesInRange.add(shout.message);
                    }
                }
                if (!messagesInRange.isEmpty()) {
                    shoutsHeard.put(shouter, messagesInRange);
                }
            }
        }
        return shoutsHeard;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    private static class Shout {
        String message;
        Coordinate coordinate;
        LocalDateTime timeOfShout;

        public Shout(String message, Coordinate coordinate, LocalDateTime timeOfShout) {
            this.message = message;
            this.coordinate = coordinate;
            this.timeOfShout = timeOfShout;
        }
    }

}
