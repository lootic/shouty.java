package shouty;

import org.junit.Test;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;

public class ShoutyTest {

    private static final String SOME_PERSON = "Sean";

    @Test
    public void shoutsShouldNotBeHeardBySelf() {
        Shouty shouty = new Shouty();
        shouty.shout(SOME_PERSON, "Hello");

        assertEquals(emptyMap(), shouty.getShoutsHeardBy(SOME_PERSON));
    }
}
