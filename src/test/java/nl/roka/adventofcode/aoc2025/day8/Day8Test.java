package nl.roka.adventofcode.aoc2025.day8;

import static org.assertj.core.api.Assertions.assertThat;

import nl.roka.adventofcode.aoc.puzzle.Answer;
import org.junit.jupiter.api.Test;

class Day8Test {

  @Test
  void silverExample() {
    assertThat(new Day8(10).runSilver()).isEqualTo(Answer.of(5 * 4 * 2));
  }

  @Test
  void goldExample() {
    assertThat(new Day8(10).runGold()).isEqualTo(Answer.TBD);
  }
}
