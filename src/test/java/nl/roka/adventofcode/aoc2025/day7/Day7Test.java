package nl.roka.adventofcode.aoc2025.day7;

import static org.assertj.core.api.Assertions.assertThat;

import nl.roka.adventofcode.aoc.puzzle.Answer;
import org.junit.jupiter.api.Test;

class Day7Test {

  @Test
  void silverExample() {
    assertThat(new Day7().runSilver()).isEqualTo(Answer.of(21));
  }

  @Test
  void goldExample() {
    assertThat(new Day7().runGold()).isEqualTo(Answer.of(40));
  }
}
