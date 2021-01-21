package shouty;

public class Shout {
    private final String shouter;
    private final Coordinate coordinate;
    private final String message;

    public Shout(String shouter, Coordinate coordinate, String message) {
        this.shouter = shouter;
        this.coordinate = coordinate;
        this.message = message;
    }

    public String getShouter() {
        return shouter;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getMessage() {
        return message;
    }
}
