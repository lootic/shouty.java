package shouty;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Clock;
import java.time.Duration;


public class ShoutSteps {
    private static final String ARBITRARY_MESSAGE = "Hello, world";
    @Autowired
    private Shouty shouty;

    @When("{word} shouts")
    public void personShouts(String person) {
        shouty.shout(person, ARBITRARY_MESSAGE);
    }

    @Then("{word} should hear {word}")
    public void listenerShouldHearShouter(String listener, String shouter) {
        if (shouter.equals("nothing")) {
            assertEquals(emptyList(), shouty.getShoutsHeardBy(listener));
        } else {
            assertEquals(1, shouty.getShoutsHeardBy(listener).size());
        }
    }

    @Then("{word} should not hear {word}")
    public void listenerShouldNotHearShouter(String listener, String shouter) {
        assertFalse(shouty.getShoutsHeardBy(listener).stream()
                .anyMatch(shout -> shout.getShouter().equals(shouter)));
    }

    @Then("{word} should hear {int} shouts from {word}")
    public void listenerShouldHearNumberOfShoutsFromShouter(String listener, int numberOfShouts, String shouter) {
        assertEquals(numberOfShouts, shouty.getShoutsHeardBy(listener).stream()
                .filter(shout -> shout.getShouter().equals(shouter))
                .count());
    }

    @Then("shout at {coordinate} from {word} times out in {int} minutes")
    public void shoutFromLucyTimesOutInMinutes(Coordinate coordinate, String shouter, int timeoutMinutes) {
        final Shout foundShout = shouty.getShoutsNear(coordinate).stream()
                .filter(shout -> shout.getShouter().equals(shouter))
                .findAny().orElseThrow(() -> new RuntimeException("Shout from " + shouter + " not found"));
        assertEquals(Duration.ofMinutes(timeoutMinutes), foundShout.getTimeout());
    }

    @When("{word} shouts with timeout {int} minutes")
    public void personShoutsWithTimeout(String person, int timeoutMinutes) {
        shouty.shout(person, ARBITRARY_MESSAGE, Duration.ofMinutes(timeoutMinutes));
    }

    @And("{int} minutes passes")
    public void minutesPasses(int minutes) {
        shouty.setClock(Clock.offset(shouty.getClock(), Duration.ofMinutes(minutes)));
    }
}
