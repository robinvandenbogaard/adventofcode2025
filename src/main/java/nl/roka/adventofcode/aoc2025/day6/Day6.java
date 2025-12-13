package nl.roka.adventofcode.aoc2025.day6;

import java.math.BigInteger;
import nl.roka.adventofcode.aoc.input.LineReader;
import nl.roka.adventofcode.aoc.puzzle.AbstractDayPuzzle;
import nl.roka.adventofcode.aoc.puzzle.Answer;
import nl.roka.adventofcode.aoc.puzzle.Day;
import nl.roka.adventofcode.aoc.puzzle.Solutions;
import nl.roka.adventofcode.aoc.runner.Runner;

public class Day6 extends AbstractDayPuzzle {

  public static final Solutions SOLUTIONS = Solutions.of("6725216329103", "10600728112865");

  void main() {
    Runner.run(new Day6());
  }

  public Day6() {
    super(new Day(6), SOLUTIONS);
  }

  public Day6(LineReader reader) {
    super(new Day(6, reader), SOLUTIONS);
  }

  @Override
  public Answer runSilver() {
    var grid = day.fullGrid();
    return Answer.of(
        ProblemProvider.ofRows(grid).stream()
            .map(MathProblem::total)
            .reduce(BigInteger.ZERO, BigInteger::add));
  }

  @Override
  public Answer runGold() {
    var grid = day.fullGrid();
    return Answer.of(
        ProblemProvider.ofColumns(grid).stream()
            .map(MathProblem::total)
            .reduce(BigInteger.ZERO, BigInteger::add));
  }
}
