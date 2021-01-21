package shouty;

import io.cucumber.java.ParameterType;

import java.time.LocalDateTime;

public class ParameterTypes {
    @ParameterType("(\\d+), (\\d+)")
    public Coordinate coordinate(String x, String y) {
        return new Coordinate(Integer.parseInt(x), Integer.parseInt(y));
    }

    @ParameterType("(\\d{2}):(\\d{2}):(\\d{2})")
    public LocalDateTime time(String hour, String minute, String second) {
        return LocalDateTime.now()
                .withHour(Integer.parseInt(hour))
                .withMinute(Integer.parseInt(minute))
                .withSecond(Integer.parseInt(second));
    }
}
