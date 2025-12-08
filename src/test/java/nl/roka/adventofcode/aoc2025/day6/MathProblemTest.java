package nl.roka.adventofcode.aoc2025.day6;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import nl.roka.adventofcode.aoc.input.Grid;
import nl.roka.adventofcode.aoc.input.LineReader;
import org.junit.jupiter.api.Test;

class MathProblemTest {
  @Test
  void addition() {
    var sum = MathProblem.addition(Grid.of(LineReader.of("/day6/problem.in")));
    assertThat(sum.total()).isEqualTo(BigInteger.valueOf(153 + 153 + 15));
  }

  @Test
  void multiplyRow() {
    var sum = MathProblem.multiplication(Grid.of(LineReader.of("/day6/problem.in")));
    assertThat(sum.total()).isEqualTo(BigInteger.valueOf(153 * 153 * 15));
  }

  @Test
  void additionCol() {
    var sum = MathProblem.additionCol(Grid.of(LineReader.of("/day6/problem.in")));
    assertThat(sum.total()).isEqualTo(BigInteger.valueOf(3 + 535 + 151 + 1));
  }

  @Test
  void multiplyCol() {
    var sum = MathProblem.multiplicationCol(Grid.of(LineReader.of("/day6/problem.in")));
    assertThat(sum.total()).isEqualTo(BigInteger.valueOf(3 * 535 * 151 * 1));
  }
}
