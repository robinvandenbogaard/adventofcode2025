package nl.roka.adventofcode.aoc2025.day1;

import java.math.BigInteger;

public record Lock(BigInteger position, int dialStoppedOnZeroCount, int dialPassedZeroCount) {
  private static final BigInteger MAX = BigInteger.valueOf(100);

  public Lock(int position) {
    this(BigInteger.valueOf(position), 0, 0);
  }

  public static Lock of(int pos, int stopped, int passed) {
    return new Lock(BigInteger.valueOf(pos), stopped, passed);
  }

  Lock dial(String value) {
    var direction = value.charAt(0);
    var steps = new BigInteger(value.substring(1));

    if (steps.compareTo(BigInteger.ZERO) == 0) {
      return this;
    }

    if (steps.compareTo(BigInteger.ZERO) < 0) {
      throw new IllegalArgumentException("Steps must be positive");
    }

    return switch (direction) {
      case 'R' -> this.add(steps);
      case 'L' -> this.subtract(steps);
      default -> throw new IllegalArgumentException("Invalid direction: " + direction);
    };
  }

  private Lock add(BigInteger steps) {
    var newPosition = position.add(steps).mod(MAX);
    var newDialStoppedOnZeroCount =
        dialStoppedOnZeroCount + (newPosition.compareTo(BigInteger.ZERO) == 0 ? 1 : 0);
    var newDialPassedZeroCount = dialPassedZeroCount + position.add(steps).divide(MAX).intValue();
    return new Lock(newPosition, newDialStoppedOnZeroCount, newDialPassedZeroCount);
  }

  private Lock subtract(BigInteger steps) {
    var newPosition = position.subtract(steps).mod(MAX);
    var newDialStoppedOnZeroCount =
        dialStoppedOnZeroCount + (newPosition.compareTo(BigInteger.ZERO) == 0 ? 1 : 0);

    var newDialPassedZeroCount = dialPassedZeroCount + steps.divide(MAX).intValue();
    var remainingSteps = steps.mod(MAX);
    if (!isZero() && position.subtract(remainingSteps).compareTo(BigInteger.ZERO) <= 0) {
      newDialPassedZeroCount += 1;
    }

    return new Lock(newPosition, newDialStoppedOnZeroCount, newDialPassedZeroCount);
  }

  private boolean isZero() {
    return position.equals(BigInteger.ZERO);
  }

  @Override
  public String toString() {
    return "Lock{"
        + "position="
        + position.intValue()
        + ", dialStoppedOnZeroCount="
        + dialStoppedOnZeroCount
        + ", dialPassedZeroCount="
        + dialPassedZeroCount
        + '}';
  }
}
