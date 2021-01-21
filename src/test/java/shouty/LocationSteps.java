package shouty;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


public class LocationSteps {
    @Autowired
    private Shouty shouty;

    @Given("{word} is at {int}, {int}")
    public void personIsAt(String person, int xCoord, int yCoord) {
        shouty.setLocation(person, new Coordinate(xCoord, yCoord));
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
