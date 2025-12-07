package nl.roka.adventofcode.aoc2025.day3;

import nl.roka.adventofcode.aoc.input.LineReader;
import nl.roka.adventofcode.aoc.puzzle.AbstractDayPuzzle;
import nl.roka.adventofcode.aoc.puzzle.Answer;
import nl.roka.adventofcode.aoc.puzzle.Day;
import nl.roka.adventofcode.aoc.puzzle.Solutions;
import nl.roka.adventofcode.aoc.runner.Runner;

import java.math.BigInteger;

public class Day3 extends AbstractDayPuzzle {

  public static final Solutions SOLUTIONS = Solutions.of("17412", "172681562473501");

  static void main(String[] args) {
    Runner.run(new Day3());
  }

  public Day3() {
    super(new Day(3), SOLUTIONS);
  }

  public Day3(LineReader reader) {
    super(new Day(3, reader), SOLUTIONS);
  }

  @Override
  public Answer runSilver() {
    return Answer.of(
        day.stream()
            .map(BatteryBank::of)
            .map(BatteryBank::maxJoltage)
            .reduce(BigInteger.ZERO, BigInteger::add));
  }

  @Override
  public Answer runGold() {
    return Answer.of(
        day.stream()
            .map(BatteryBank::of)
            .map(BatteryBank::maxAmpedJoltage)
            .reduce(BigInteger.ZERO, BigInteger::add));
  }
}
