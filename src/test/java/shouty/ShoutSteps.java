package shouty;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.datatable.DataTable;
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
    public void person_is_at(String name, int xCoord, int yCoord) {
        shouty.setLocation(name, new Coordinate(xCoord, yCoord));
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

    @Given("people are located at")
    public void peopleAreLocatedAt(List<PersonLocation> personLocations) {
//        dataTable.asList().get(1).get(0) //FIXME - does not work

        personLocations.forEach( personLocation ->
                shouty.setLocation(personLocation.name, new Coordinate(personLocation.x, personLocation.y)));
    }

    @DataTableType
    public PersonLocation definePersonLocation(Map<String, String> entry) {
        return new PersonLocation(
                entry.get("name"),
                Integer.parseInt(entry.get("x")),
                Integer.parseInt(entry.get("y"))
        );
    }

    private static class PersonLocation {
        private final String name;
        private final int x;
        private final int y;

        public PersonLocation(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }
}
