package nl.roka.adventofcode.aoc2025.day4;

import java.util.function.Predicate;
import nl.roka.adventofcode.aoc.input.Grid;
import nl.roka.adventofcode.aoc.input.LineReader;
import nl.roka.adventofcode.aoc.input.Point;
import nl.roka.adventofcode.aoc.puzzle.AbstractDayPuzzle;
import nl.roka.adventofcode.aoc.puzzle.Answer;
import nl.roka.adventofcode.aoc.puzzle.Day;
import nl.roka.adventofcode.aoc.puzzle.Solutions;
import nl.roka.adventofcode.aoc.runner.Runner;

public class Day4 extends AbstractDayPuzzle {

  public static final Solutions SOLUTIONS = Solutions.of("1489", "8890");

  void main() {
    Runner.run(new Day4());
  }

  public Day4() {
    super(new Day(4), SOLUTIONS);
  }

  public Day4(LineReader reader) {
    super(new Day(4, reader), SOLUTIONS);
  }

  @Override
  public Answer runSilver() {
    Grid grid = day.fullGrid();

    Predicate<Point> hasPaper = p -> grid.get(p).equals("@");

    long count =
        grid.points()
            .filter(hasPaper)
            .filter(
                point ->
                    point.adjecent().stream().filter(grid::inBounds).filter(hasPaper).count() < 4)
            .count();
    return Answer.of(count);
  }

  @Override
  public Answer runGold() {
    Grid grid = day.fullGrid();

    Predicate<Point> hasPaper = p -> grid.get(p).equals("@");
    long removed = 0;
    long removedLastCall;
    do {
      var toRemove =
          grid.points()
              .filter(hasPaper)
              .filter(
                  point ->
                      point.adjecent().stream().filter(grid::inBounds).filter(hasPaper).count() < 4)
              .toList();

      toRemove.forEach(p -> grid.set(p, "X"));

      removedLastCall = toRemove.size();
      removed += toRemove.size();
    } while (removedLastCall != 0);
    return Answer.of(removed);
  }
}
