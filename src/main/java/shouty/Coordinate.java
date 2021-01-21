package shouty;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }

    public int distanceFrom(Coordinate other) {
        return (int) Math.sqrt(Math.pow(Math.abs(x - other.x), 2) + Math.pow(Math.abs(y - other.y), 2));
    }
}
