package nl.roka.adventofcode.aoc2025.day6;

import nl.roka.adventofcode.aoc.input.Grid;
import nl.roka.adventofcode.aoc.input.LineReader;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class MathProblemTest {
  @Test
  void addition() {
    var sum = MathProblem.addition(Grid.of(LineReader.of("/day6/problem.in")));
    assertThat(sum.total()).isEqualTo(BigInteger.valueOf(100 + 100 + 10));
  }

  @Test
  void multiply() {
    var sum = MathProblem.multiplication(Grid.of(LineReader.of("/day6/problem.in")));
    assertThat(sum.total()).isEqualTo(BigInteger.valueOf(100 * 100 * 10));
  }
}
