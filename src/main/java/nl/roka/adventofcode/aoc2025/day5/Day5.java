package nl.roka.adventofcode.aoc2025.day5;

import java.math.BigInteger;
import java.util.ArrayList;
import nl.roka.adventofcode.aoc.input.Line;
import nl.roka.adventofcode.aoc.input.LineReader;
import nl.roka.adventofcode.aoc.puzzle.AbstractDayPuzzle;
import nl.roka.adventofcode.aoc.puzzle.Answer;
import nl.roka.adventofcode.aoc.puzzle.Day;
import nl.roka.adventofcode.aoc.puzzle.Solutions;
import nl.roka.adventofcode.aoc.runner.Runner;

public class Day5 extends AbstractDayPuzzle {

  public static final Solutions SOLUTIONS = Solutions.of("756", "355555479253787");

  void main() {
    Runner.run(new Day5());
  }

  public Day5() {
    super(new Day(5), SOLUTIONS);
  }

  public Day5(LineReader reader) {
    super(new Day(5, reader), SOLUTIONS);
  }

  @Override
  public Answer runSilver() {
    var ranges = new ArrayList<IdRange>();
    var ids = new ArrayList<Id>();
    day.stream()
        .filter(Line::isNotBlank)
        .forEach(
            line -> {
              if (line.contains("-")) ranges.add(IdRange.of(line.text()));
              else ids.add(Id.of(line.text()));
            });

    var freshIds =
        ids.stream().filter(id -> ranges.stream().anyMatch(range -> range.inRange(id))).count();
    return Answer.of(freshIds);
  }

  @Override
  public Answer runGold() {
    var ranges =
        day.stream().filter(line -> line.contains("-")).map(Line::text).map(IdRange::of).toList();

    var withoutOverlap = new RangeMerger(ranges).merged();

    return Answer.of(
        withoutOverlap.stream().map(IdRange::length).reduce(BigInteger.ZERO, BigInteger::add));
  }
}
