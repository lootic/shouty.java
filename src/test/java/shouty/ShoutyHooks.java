package shouty;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ShoutyHooks {
    @Before(order = 4)
    public void beforeEarly() {
        System.out.println("Before early!!!");
    }

    @Before
    public void before() {
        System.out.println("Before!!!");
    }

    @After
    public void after() {
        System.out.println("After!!!");
    }
}
