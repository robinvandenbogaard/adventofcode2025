package nl.roka.adventofcode.aoc2025.day6;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.List;
import nl.roka.adventofcode.aoc.input.Grid;
import nl.roka.adventofcode.aoc.input.LineReader;
import org.junit.jupiter.api.Test;

class ProblemProviderTest {

  @Test
  void findsRowProblems() {
    var finder = ProblemProvider.ofRows(Grid.of(LineReader.of("/day6.in")));
    assertThat(finder.problems())
        .containsExactlyInAnyOrder(
            new MathProblem(
                MathType.MULTIPLY,
                List.of(BigInteger.valueOf(123), BigInteger.valueOf(45), BigInteger.valueOf(6))),
            new MathProblem(
                MathType.ADD,
                List.of(BigInteger.valueOf(328), BigInteger.valueOf(64), BigInteger.valueOf(98))),
            new MathProblem(
                MathType.MULTIPLY,
                List.of(BigInteger.valueOf(51), BigInteger.valueOf(387), BigInteger.valueOf(215))),
            new MathProblem(
                MathType.ADD,
                List.of(BigInteger.valueOf(64), BigInteger.valueOf(23), BigInteger.valueOf(314))));
  }

  @Test
  void findsColProblems() {
    var finder = ProblemProvider.ofColumns(Grid.of(LineReader.of("/day6.in")));
    assertThat(finder.problems())
        .containsExactlyInAnyOrder(
            new MathProblem(
                MathType.ADD,
                List.of(BigInteger.valueOf(4), BigInteger.valueOf(431), BigInteger.valueOf(623))),
            new MathProblem(
                MathType.MULTIPLY,
                List.of(BigInteger.valueOf(175), BigInteger.valueOf(581), BigInteger.valueOf(32))),
            new MathProblem(
                MathType.ADD,
                List.of(BigInteger.valueOf(8), BigInteger.valueOf(248), BigInteger.valueOf(369))),
            new MathProblem(
                MathType.MULTIPLY,
                List.of(BigInteger.valueOf(356), BigInteger.valueOf(24), BigInteger.valueOf(1))));
  }
}
