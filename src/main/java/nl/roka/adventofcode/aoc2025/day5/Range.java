package nl.roka.adventofcode.aoc2025.day5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

record Range(BigInteger min, BigInteger max) implements Comparable<Range> {
  Range(BigInteger min, BigInteger max) {
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

  public Stream<Id> ids() {
    var ids = new ArrayList<Id>();
    for (BigInteger i = min; i.compareTo(max) <= 0; i = i.add(BigInteger.ONE)) {
      ids.add(Id.of(i));
    }
    return ids.stream();
  }

  public boolean inRange(Id id) {
    return id.gte(min) && id.lte(max);
  }

  public boolean overLaps(Range other) {
    return inRange(Id.of(other.min)) || inRange(Id.of(other.max));
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
