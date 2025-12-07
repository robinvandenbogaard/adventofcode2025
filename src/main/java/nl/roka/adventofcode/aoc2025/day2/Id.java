package nl.roka.adventofcode.aoc2025.day2;

import java.math.BigInteger;

record Id(String value) {

  public static Id of(BigInteger value) {
    return new Id(value.toString());
  }

  public static Id of(int value) {
    return new Id(Integer.toString(value));
  }

  public Id add(Id other) {
    return Id.of(new BigInteger(value).add(new BigInteger(other.value)));
  }

  public boolean isRepeatedTwice() {
    if (value.length() % 2 == 1) return false;
    var part1 = value.substring(0, value.length() / 2);
    var part2 = value.substring(value.length() / 2);
    return part1.equals(part2);
  }

  public boolean isNotRepeatedTwice() {
    return !isRepeatedTwice();
  }

  public boolean isRepeatedPattern() {
    boolean patternFound = false;
    for (int i = 1; i < value.length() && !patternFound; i++) {
      patternFound = value.replaceAll(value.substring(0, i), "").isEmpty();
    }
    return patternFound;
  }

  public boolean isNotRepeatedPattern() {
    return !isRepeatedPattern();
  }
}
