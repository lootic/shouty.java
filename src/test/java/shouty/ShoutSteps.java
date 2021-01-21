package shouty;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ShoutSteps {
    private static final String ARBITRARY_MESSAGE = "Hello, world";
    @Autowired
    private Shouty shouty;

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

    @Then("{word} should hear {int} shouts from {word}")
    public void listenerShouldHearNumberOfShoutsFromShouter(String listener, int numberOfShouts, String shouter) {
        assertEquals(numberOfShouts, shouty.getShoutsHeardBy(listener).get(shouter).size());
    }
}
