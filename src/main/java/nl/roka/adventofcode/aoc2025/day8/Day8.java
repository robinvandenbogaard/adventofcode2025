package nl.roka.adventofcode.aoc2025.day8;

import java.math.BigInteger;
import java.util.List;
import nl.roka.adventofcode.aoc.input.LineReader;
import nl.roka.adventofcode.aoc.puzzle.AbstractDayPuzzle;
import nl.roka.adventofcode.aoc.puzzle.Answer;
import nl.roka.adventofcode.aoc.puzzle.Day;
import nl.roka.adventofcode.aoc.puzzle.Solutions;
import nl.roka.adventofcode.aoc.runner.RequireOptimization;
import nl.roka.adventofcode.aoc.runner.Runner;

@RequireOptimization
public class Day8 extends AbstractDayPuzzle {

  public static final Solutions SOLUTIONS = Solutions.of(131150, 2497445);
  private final int maxConnections;

  void main() {
    Runner.run(new Day8(1000));
  }

  public Day8() {
    super(new Day(8), SOLUTIONS);
    this.maxConnections = 1000;
  }

  public Day8(int maxConnections) {
    super(new Day(8), SOLUTIONS);
    this.maxConnections = maxConnections;
  }

  public Day8(LineReader reader) {
    super(new Day(8, reader), SOLUTIONS);
    this.maxConnections = 1000;
  }

  @Override
  public Answer runSilver() {
    var boxes = new AllBoxes(day.stream().map(FusionBox::of).toList());
    var circuits = new Circuits();
    boxes.sortByDistance().stream().limit(maxConnections).forEach(circuits::add);

    return Answer.of(
        circuits.stream()
            .sorted()
            .map(Circuit::size)
            .limit(3)
            .reduce(BigInteger.ONE, BigInteger::multiply));
  }

  @Override
  public Answer runGold() {
    var boxes = new AllBoxes(day.stream().map(FusionBox::of).toList());
    var circuits = new Circuits();
    List<FusionBoxPair> sortByDistance = boxes.sortByDistance();
    for (FusionBoxPair pair : sortByDistance) {
      circuits.add(pair);
      if (circuits.circuits().size() == 1 && circuits.boxCount() == boxes.count()) {
        return Answer.of(pair.multiplyX());
      }
    }

    throw new IllegalStateException("Never merged back to one");
  }
}
