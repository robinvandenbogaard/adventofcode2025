package nl.roka.adventofcode.aoc2025.day5;

import java.math.BigInteger;

record Id(BigInteger value) {

  public static Id of(BigInteger value) {
    return new Id(value);
  }

  public static Id of(String value) {
    return new Id(new BigInteger(value));
  }

  public static Id of(int value) {
    return new Id(BigInteger.valueOf(value));
  }

  public Id add(Id other) {
    return Id.of(value.add(other.value));
  }
}
