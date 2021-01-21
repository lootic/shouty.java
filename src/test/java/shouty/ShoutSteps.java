package shouty;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;


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

    @Given("people are located at")
    public void peopleAreLocatedAt(List<PersonLocation> personLocations) {
        for (PersonLocation personLocation : personLocations) {
            shouty.setLocation(personLocation.name, new Coordinate(personLocation.x, personLocation.y));
        }
    }

    private static class PersonLocation {
        String name;
        int x, y;

        public PersonLocation(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }

    @SuppressWarnings("unused")
    @DataTableType
    public PersonLocation definePersonLocation(Map<String, String> entry) {
        return new PersonLocation(
                entry.get("name"),
                Integer.parseInt(entry.get("x")),
                Integer.parseInt(entry.get("y"))
        );
    }
}
