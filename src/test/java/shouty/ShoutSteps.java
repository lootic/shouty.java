package shouty;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ShoutSteps {
    private static final String ARBITRARY_MESSAGE = "Hello, world";
    private final Shouty shouty;

    @Autowired
    public ShoutSteps(Shouty shouty) {
        this.shouty = shouty;
    }

    @When("{word} shouts")
    public void person_shouts(String name) {
        shouty.shout(name, ARBITRARY_MESSAGE);
    }

    @Then("{word} should hear {word}")
    public void listener_should_hear_shouter(String listener, String shouter) {
        Map<String, List<String>> heardShouts = shouty.getShoutsHeardBy(listener);
        if ("nothing".equals(shouter)) {
            assertEquals(emptyMap(), shouty.getShoutsHeardBy(listener));
        } else {
            boolean canHearShouter = heardShouts.containsKey(shouter);
            assertTrue(listener + " cannot hear " + shouter, canHearShouter);
        }
    }

    @Then("{word} should not hear {word}")
    public void listener_should_not_hear_shouter(String listener, String shouter) {
        Map<String, List<String>> heardShouts = shouty.getShoutsHeardBy(listener);
        boolean canHearShouter = heardShouts.containsKey(shouter);
        assertFalse(listener + " can hear " + shouter, canHearShouter);
    }

    @Then("{word} should hear {int} shouts from {word}")
    public void listener_should_hear_shouts_from_shouter(String listener, int numberOfShouts, String shouter) {
        assertEquals(numberOfShouts, shouty.getShoutsHeardBy(listener).get(shouter).size());
    }

    @Given("the time is {time}")
    public void the_time_is(LocalDateTime currentTime) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
