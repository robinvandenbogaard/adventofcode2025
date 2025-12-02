package nl.roka.adventofcode.aoc.puzzle;

import java.math.BigInteger;

public record Answer(String text) {

  public static final Answer TBD = new Answer("TBD");

  public static Answer of(int value) {
    return of("" + value);
  }

  public static Answer of(BigInteger value) {
    return of(value.toString());
  }

  public static Answer of(long value) {
    return of("" + value);
  }

  public static Answer of(String text) {
    return new Answer(text);
  }

  public static Answer of() {
    return TBD;
  }

  public boolean isNotCalculated() {
    return this.equals(TBD);
  }
}
