package shouty;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ShoutSteps {
    private static final String ARBITRARY_MESSAGE = "Hello, world";
    private final Shouty shouty = new Shouty();

    @Given("{word} is at {int}, {int}")
    public void personIsAt(String person, int xCoord, int yCoord) {
        shouty.setLocation(person, new Coordinate(xCoord, yCoord));
    }

    @When("{word} shouts")
    public void personShouts(String person) {
        shouty.shout(person, ARBITRARY_MESSAGE);
    }

    @Then("{word} should hear Sean")
    public void listenerShouldHearShouter(String listener) {
        assertEquals(1, shouty.getShoutsHeardBy(listener).size());
    }

    @Then("{word} should hear nothing")
    public void listenerShouldHereNothing(String listener) {
        assertEquals(emptyMap(), shouty.getShoutsHeardBy(listener));
    }

    @Then("{word} should not hear {word}")
    public void listenerShouldNotHearShouter(String listener, String shouter) {
        assertNull(shouty.getShoutsHeardBy(listener).get(shouter));
    }
}
