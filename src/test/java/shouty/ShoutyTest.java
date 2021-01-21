package shouty;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

        assertEquals(List.of(new Shout(SOME_PERSON, origin, SOME_MESSAGE)), shouts);
    }
}
