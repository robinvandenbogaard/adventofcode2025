package nl.roka.adventofcode.aoc.input;

import java.util.List;

public record Point(int x, int y) {

  public static final Point ZERO = new Point(0, 0);

  public static Point NORTH = ZERO.north();
  public static Point SOUTH = ZERO.south();
  public static Point WEST = ZERO.west();
  public static Point EAST = ZERO.east();
  public static Point NORTHEAST = ZERO.northEast();
  public static Point SOUTHWEST = ZERO.southWest();
  public static Point NORTHWEST = ZERO.northWest();
  public static Point SOUTHEAST = ZERO.southEast();

  public static Point of(int x, int y) {
    return new Point(x, y);
  }

  public List<Point> adjecent() {
    return List.of(
        Point.of(x - 1, y - 1),
        Point.of(x, y - 1),
        Point.of(x + 1, y - 1),
        Point.of(x - 1, y),
        Point.of(x + 1, y),
        Point.of(x - 1, y + 1),
        Point.of(x, y + 1),
        Point.of(x + 1, y + 1));
  }

  public Point setY(int y) {
    return Point.of(x, y);
  }

  public Point north() {
    return this.add(-1, 0);
  }

  public Point south() {
    return this.add(1, 0);
  }

  public Point east() {
    return this.add(0, 1);
  }

  public Point west() {
    return this.add(0, -1);
  }

  public Point northEast() {
    return this.north().east();
  }

  public Point northWest() {
    return this.north().west();
  }

  public Point southEast() {
    return this.south().east();
  }

  public Point southWest() {
    return this.south().west();
  }

  public Point add(int x, int y) {
    return Point.of(this.x + x, this.y + y);
  }

  public Point add(Point point) {
    return Point.of(this.x + point.x, this.y + point.y);
  }

  public Point turnRight() {
    if (this.equals(NORTH)) return EAST;
    else if (this.equals(SOUTH)) {
      return WEST;

    } else if (this.equals(EAST)) {
      return SOUTH;
    } else return NORTH;
  }

  public Point distance(Point point) {
    return Point.of(point.x - this.x, point.y - this.y);
  }

  public Point minus(Point point) {
    return Point.of(this.x - point.x, this.y - point.y);
  }
}
