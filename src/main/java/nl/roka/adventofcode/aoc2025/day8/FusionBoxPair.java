package nl.roka.adventofcode.aoc2025.day8;

import java.math.BigDecimal;
import java.math.BigInteger;

public record FusionBoxPair(FusionBox box1, FusionBox box2) implements Comparable<FusionBoxPair> {

  @Override
  public int compareTo(FusionBoxPair o) {
    return distance().compareTo(o.distance());
  }

  private BigDecimal distance() {
    return box1.distance(box2);
  }

  public BigInteger multiplyX() {
    BigInteger result =
        BigInteger.valueOf(box1.point3D().x()).multiply(BigInteger.valueOf(box2.point3D().x()));
    System.out.printf("%d * %d = %d", box1.point3D().x(), box2.point3D().x(), result);
    return result;
  }
}
