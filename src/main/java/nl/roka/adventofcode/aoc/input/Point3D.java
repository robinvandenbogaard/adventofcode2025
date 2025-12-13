package nl.roka.adventofcode.aoc.input;

import java.math.BigDecimal;
import java.math.MathContext;

public record Point3D(int x, int y, int z) {

  public static final Point3D ZERO = new Point3D(0, 0, 0);

  public static Point3D of(int x, int y, int z) {
    return new Point3D(x, y, z);
  }

  public BigDecimal distance(Point3D point) {
    return new BigDecimal(
        Math.sqrt(
            (Math.pow(point.x() - x, 2) + Math.pow(point.y() - y, 2) + Math.pow(point.z() - z, 2))),
        new MathContext(8));
  }
}
