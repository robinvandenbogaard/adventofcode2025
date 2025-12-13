package nl.roka.adventofcode.aoc2025.day8;

import java.math.BigDecimal;

public record FusionBoxPair(FusionBox box1, FusionBox box2) implements Comparable<FusionBoxPair> {

  @Override
  public int compareTo(FusionBoxPair o) {
    return distance().compareTo(o.distance());
  }

  private BigDecimal distance() {
    return box1.distance(box2);
  }

  @Override
  public String toString() {
    return "Pair distance: " + distance();
  }
}
