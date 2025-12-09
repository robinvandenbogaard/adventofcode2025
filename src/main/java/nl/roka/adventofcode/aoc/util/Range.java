package nl.roka.adventofcode.aoc.util;

import java.math.BigInteger;
import java.util.Comparator;

public record Range(BigInteger min, BigInteger max) implements Comparable<Range> {
  public Range(BigInteger min, BigInteger max) {
    this.min = min;
    this.max = max;
    if (min.compareTo(max) > 0) {
      throw new IllegalArgumentException("Min must be less than max");
    }

    if (min.compareTo(BigInteger.ZERO) <= 0) {
      throw new IllegalArgumentException("Min must greater than zero");
    }
  }

  public static Range of(String input) {
    var split = input.split("-");

    return new Range(new BigInteger(split[0]), new BigInteger(split[1]));
  }

  public BigInteger length() {
    return max.subtract(min).add(BigInteger.ONE);
  }

  /** Inclusive check if the value is in the range. */
  public boolean inRange(BigInteger value) {
    return gte(value, min) && lte(value, max);
  }

  public boolean overLaps(Range other) {
    return inRange(other.min) || inRange(other.max);
  }

  private boolean gte(BigInteger value, BigInteger other) {
    return value.compareTo(other) >= 0;
  }

  public boolean lte(BigInteger value, BigInteger other) {
    return value.compareTo(other) <= 0;
  }

  public Range merge(Range other) {
    if (!overLaps(other)) throw new IllegalArgumentException("Ranges must overlap");
    return new Range(min.min(other.min), max.max(other.max));
  }

  @Override
  public int compareTo(Range other) {
    return Comparator.comparing(Range::min).thenComparing(Range::max).compare(this, other);
  }
}
