package nl.roka.adventofcode.aoc2025.day5;

import java.math.BigInteger;

record Id(String value) {

  public static Id of(BigInteger value) {
    return new Id(value.toString());
  }

  public static Id of(String value) {
    return new Id(value);
  }

  public static Id of(int value) {
    return new Id(Integer.toString(value));
  }

  public Id add(Id other) {
    return Id.of(new BigInteger(value).add(new BigInteger(other.value)));
  }

  public boolean gte(BigInteger other) {
    return new BigInteger(value).compareTo(other) >= 0;
  }

  public boolean lte(BigInteger other) {
    return new BigInteger(value).compareTo(other) <= 0;
  }
}
