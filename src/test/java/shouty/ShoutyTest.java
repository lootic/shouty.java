package shouty;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ShoutyTest {
    @Test
    public void shouldNotHearSelf() {
        Shouty shouty = new Shouty();
        shouty.setLocation("Lucy", new Coordinate(0, 0));
        shouty.shout("Lucy", "HEEEEEJJJ!!!");
        assertEquals(Collections.emptyMap(), shouty.getShoutsHeardBy("Lucy"));
    }
}
