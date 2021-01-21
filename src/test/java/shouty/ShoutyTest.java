package shouty;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ShoutyTest {
    @Test
    public void shouldNotHearSelf() {
        Shouty shouty = new Shouty();
        shouty.shout("Lucy", "HEEEEEJJJ!!!");
        assertEquals(Collections.emptyMap(), shouty.getShoutsHeardBy("Lucy"));
    }
}
