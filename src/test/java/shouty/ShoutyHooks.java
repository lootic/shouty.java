package shouty;


import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ShoutyHooks {

    @Before(value = "@wip or @other", order = 1)
    public void setup(){
        System.out.println("BEFORE WIP");
    }

    @After
    public void teardown(){
        System.out.println("AFTER");
    }


}

