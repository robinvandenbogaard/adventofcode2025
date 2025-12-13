package nl.roka.adventofcode.aoc2025.day2;

import java.math.BigInteger;
import java.util.Arrays;
import nl.roka.adventofcode.aoc.input.LineReader;
import nl.roka.adventofcode.aoc.puzzle.AbstractDayPuzzle;
import nl.roka.adventofcode.aoc.puzzle.Answer;
import nl.roka.adventofcode.aoc.puzzle.Day;
import nl.roka.adventofcode.aoc.puzzle.Solutions;
import nl.roka.adventofcode.aoc.runner.Runner;

public class Day2 extends AbstractDayPuzzle {

  public static final Solutions SOLUTIONS = Solutions.of("12599655151", "20942028255");

  void main() {
    Runner.run(new Day2());
  }

  public Day2() {
    super(new Day(2), SOLUTIONS);
  }

  public Day2(LineReader reader) {
    super(new Day(2, reader), SOLUTIONS);
  }

  @Override
  public Answer runSilver() {
    var line = day.stream().findFirst().orElseThrow().text();

    return Answer.of(
        Arrays.stream(line.split(","))
            .map(Range::of)
            .map(Range::sumIdsRepeatedTwice)
            .reduce(BigInteger.ZERO, BigInteger::add));
  }

  @Override
  public Answer runGold() {
    var line = day.stream().findFirst().orElseThrow().text();

    return Answer.of(
        Arrays.stream(line.split(","))
            .map(Range::of)
            .map(Range::sumIdsRepeatedPattern)
            .reduce(BigInteger.ZERO, BigInteger::add));
  }
}
