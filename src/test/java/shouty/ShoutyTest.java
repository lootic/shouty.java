package shouty;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.util.List;

public class ShoutyTest {

    private static final String SOME_PERSON = "Sean";
    public static final String SOME_MESSAGE = "Hello";

    @Test
    public void shoutsShouldNotBeHeardBySelf() {
        Shouty shouty = new Shouty();
        shouty.setLocation(SOME_PERSON, new Coordinate(0, 0));
        shouty.shout(SOME_PERSON, SOME_MESSAGE);

        assertEquals(emptyList(), shouty.getShoutsHeardBy(SOME_PERSON));
    }

    @Test
    public void shoutDistanceCalculatedAtShoutingTimestamp() {
        Shouty shouty = new Shouty();
        final Coordinate origin = new Coordinate(0, 0);
        shouty.setLocation(SOME_PERSON, origin);
        shouty.shout(SOME_PERSON, SOME_MESSAGE);
        shouty.setLocation(SOME_PERSON, new Coordinate(0, 1100));

        final List<Shout> shouts = shouty.getShoutsNear(origin);

        assertEquals(1, shouts.size());
        final Shout shout = shouts.get(0);
        assertEquals(SOME_PERSON, shout.getShouter());
        assertEquals(origin, shout.getCoordinate());
        assertEquals(SOME_MESSAGE, shout.getMessage());
    }

    @Test
    public void defaultShoutExpiryIs5Minutes() {
        Shouty shouty = new Shouty();
        final Coordinate origin = new Coordinate(0, 0);
        shouty.setLocation(SOME_PERSON, origin);
        shouty.shout(SOME_PERSON, SOME_MESSAGE);

        final List<Shout> shouts = shouty.getShoutsNear(origin);

        assertEquals(1, shouts.size());
        assertEquals(Duration.ofMinutes(5), shouts.get(0).getTimeout());
    }

    @Test
    public void canShoutWithCustomShoutTimeout() {
        Shouty shouty = new Shouty();
        final Coordinate origin = new Coordinate(0, 0);
        shouty.setLocation(SOME_PERSON, origin);
        shouty.shout(SOME_PERSON, SOME_MESSAGE, Duration.ofSeconds(10));

        final List<Shout> shouts = shouty.getShoutsNear(origin);

        assertEquals(1, shouts.size());
        assertEquals(Duration.ofSeconds(10), shouts.get(0).getTimeout());
    }

    @Test
    public void cannotHearShoutAfterTimeout() {
        Shouty shouty = new Shouty();
        final Coordinate origin = new Coordinate(0, 0);
        shouty.setLocation(SOME_PERSON, origin);
        shouty.shout(SOME_PERSON, SOME_MESSAGE, Duration.ofSeconds(10));

        shouty.setClock(Clock.offset(shouty.getClock(), Duration.ofMinutes(1)));

        final List<Shout> shouts = shouty.getShoutsNear(origin);
        assertEquals(emptyList(), shouts);
    }
}
