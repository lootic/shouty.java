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

    @Test
    public void shoutsShouldNotMoveWithShouters() {
        Shouty shouty = new Shouty();
        shouty.setLocation("Sean", new Coordinate(0, 1001));
        shouty.setLocation("Lucy", new Coordinate(0, 0));
        shouty.shout("Sean", "Outside range!!!!");
        shouty.setLocation("Sean", new Coordinate(0, 999));
        shouty.shout("Sean", "Inside range!!!!");

        assertEquals(1, shouty.getShoutsHeardBy("Lucy").get("Sean").size());
    }

//    @Test
//    public void shouldNotHearShoutsWhenMovingIntoRange() {
//        Shouty shouty = new Shouty();
//        shouty.setLocation("Sean", new Coordinate(0, 1001));
//        shouty.setLocation("Lucy", new Coordinate(0, 0));
//        shouty.shout("Sean", "Outside range!!!!");
//        shouty.setLocation("Lucy", new Coordinate(0, 999));
//        shouty.shout("Sean", "Inside range!!!!");
//
//        assertEquals(1, shouty.getShoutsHeardBy("Lucy").get("Sean").size());
//    }
}
