package nl.roka.adventofcode.aoc2025.day8;

import nl.roka.adventofcode.aoc.input.Line;
import nl.roka.adventofcode.aoc.input.Point3D;

import java.math.BigDecimal;

public record FusionBox(Point3D point3D) {
  static FusionBox of(Line line) {
    var split = line.text().split(",");
    return new FusionBox(
        new Point3D(
            Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
  }

  public BigDecimal distance(FusionBox other) {
    return point3D.distance(other.point3D());
  }
}
