package shouty;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class LocationSteps {
    private final Shouty shouty;

    @Autowired
    public LocationSteps(Shouty shouty) {
        this.shouty = shouty;
    }

    @Given("{word} is at {int}, {int}")
    public void person_is_at(String name, int xCoord, int yCoord) {
        shouty.setLocation(name, new Coordinate(xCoord, yCoord));
    }

    @Given("people are located at")
    public void peopleAreLocatedAt(List<PersonLocation> personLocations) {
//        dataTable.asList().get(1).get(0) //FIXME - does not work

        personLocations.forEach(personLocation ->
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
