package nl.roka.adventofcode.aoc2025.day4;

import static org.assertj.core.api.Assertions.assertThat;

import nl.roka.adventofcode.aoc.puzzle.Answer;
import org.junit.jupiter.api.Test;

class Day4Test {

  @Test
  void silverExample() {
    assertThat(new Day4().runSilver()).isEqualTo(Answer.of(13));
  }

  @Test
  void goldExample() {
    assertThat(new Day4().runGold()).isEqualTo(Answer.of(43));
  }
}
