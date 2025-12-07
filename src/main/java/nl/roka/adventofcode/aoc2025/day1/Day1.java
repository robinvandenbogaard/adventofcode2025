package nl.roka.adventofcode.aoc2025.day1;

import nl.roka.adventofcode.aoc.input.LineReader;
import nl.roka.adventofcode.aoc.puzzle.AbstractDayPuzzle;
import nl.roka.adventofcode.aoc.puzzle.Answer;
import nl.roka.adventofcode.aoc.puzzle.Day;
import nl.roka.adventofcode.aoc.puzzle.Solutions;
import nl.roka.adventofcode.aoc.runner.Runner;

public class Day1 extends AbstractDayPuzzle {

  public static final Solutions SOLUTIONS = Solutions.of(1118, 6289);

  static void main(String[] args) {
    Runner.run(new Day1());
  }

  public Day1() {
    super(new Day(1), SOLUTIONS);
  }

  public Day1(LineReader reader) {
    super(new Day(1, reader), SOLUTIONS);
  }

  @Override
  public Answer runSilver() {
    var lock = new Lock(50);
    var lines = day.stream().toList();
    for (var line : lines) {
      lock = lock.dial(line.text());
    }
    return Answer.of(lock.dialStoppedOnZeroCount());
  }

  @Override
  public Answer runGold() {
    var lock = new Lock(50);
    var lines = day.stream().toList();
    for (var line : lines) {
      lock = lock.dial(line.text());
    }
    return Answer.of(lock.dialPassedZeroCount());
  }
}
