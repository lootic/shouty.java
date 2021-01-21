package shouty;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }

    public int distanceFrom(Coordinate other) {
        final double xDiff = (double) x - other.x;
        final double yDiff = (double) y - other.y;
        return (int) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
}
