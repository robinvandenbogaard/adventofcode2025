package nl.roka.adventofcode.aoc2025.day5;

import static org.assertj.core.api.Assertions.assertThat;

import nl.roka.adventofcode.aoc.puzzle.Answer;
import org.junit.jupiter.api.Test;

class Day5Test {

  @Test
  void silverExample() {
    assertThat(new Day5().runSilver()).isEqualTo(Answer.of(3));
  }

  @Test
  void goldExample() {
    assertThat(new Day5().runGold()).isEqualTo(Answer.of(14));
  }
}
