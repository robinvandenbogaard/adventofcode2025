package nl.roka.adventofcode.aoc2025.day7;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import nl.roka.adventofcode.aoc.input.LineReader;
import nl.roka.adventofcode.aoc.puzzle.AbstractDayPuzzle;
import nl.roka.adventofcode.aoc.puzzle.Answer;
import nl.roka.adventofcode.aoc.puzzle.Day;
import nl.roka.adventofcode.aoc.puzzle.Solutions;
import nl.roka.adventofcode.aoc.runner.Runner;

public class Day7 extends AbstractDayPuzzle {

  public static final Solutions SOLUTIONS = Solutions.of("1560", "25592971184998");

  void main() {
    Runner.run(new Day7());
  }

  public Day7() {
    super(new Day(7), SOLUTIONS);
  }

  public Day7(LineReader reader) {
    super(new Day(7, reader), SOLUTIONS);
  }

  @Override
  public Answer runSilver() {
    var grid = day.fullGrid();
    var startPoint = grid.findSymbol("S");
    var beams = new ArrayList<Beam>();
    beams.add(new Beam(grid, startPoint));
    var totalSplits = 0L;
    while (!beams.isEmpty()) {
      var newBeams =
          beams.stream().map(Beam::moveDown).flatMap(Set::stream).collect(Collectors.toSet());
      var splitBeams = new ArrayList<>(beams.stream().filter(Beam::split).toList());
      newBeams.removeIf(beams::contains);
      beams.removeIf(Beam::destroyed);
      beams.addAll(newBeams);
      totalSplits += splitBeams.size();
    }

    return Answer.of(totalSplits);
  }

  @Override
  public Answer runGold() {
    var grid = day.fullGrid();
    var startPoint = grid.findSymbol("S");
    var beams = new ArrayList<Beam>();
    beams.add(new Beam(grid, startPoint));
    var numberOfBeams = BigInteger.ZERO;
    while (!beams.isEmpty()) {
      var newBeams = beams.stream().map(Beam::moveDownWithWeight).flatMap(Set::stream).toList();
      var finished = beams.stream().filter(Beam::destroyed).toList();
      beams.addAll(newBeams);
      beams =
          new ArrayList<>(
              beams.stream()
                  .collect(Collectors.toMap(Beam::position, Function.identity(), Beam::merge))
                  .values());
      beams.removeAll(finished);

      numberOfBeams = finished.stream().map(Beam::weight).reduce(BigInteger.ZERO, BigInteger::add);
    }

    return Answer.of(numberOfBeams);
  }
}
