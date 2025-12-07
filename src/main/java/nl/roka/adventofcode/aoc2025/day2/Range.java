package nl.roka.adventofcode.aoc2025.day2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Stream;

class Range {
  private final BigInteger min;
  private final BigInteger max;

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

  public Stream<Id> ids() {
    var ids = new ArrayList<Id>();
    for (BigInteger i = min; i.compareTo(max) <= 0; i = i.add(BigInteger.ONE)) {
      ids.add(Id.of(i));
    }
    return ids.stream();
  }

  public BigInteger sumIdsRepeatedTwice() {
    return new BigInteger(ids().filter(Id::isRepeatedTwice).reduce(Id.of(0), Id::add).value());
  }

  public BigInteger sumIdsRepeatedPattern() {
    return new BigInteger(ids().filter(Id::isRepeatedPattern).reduce(Id.of(0), Id::add).value());
  }
}
