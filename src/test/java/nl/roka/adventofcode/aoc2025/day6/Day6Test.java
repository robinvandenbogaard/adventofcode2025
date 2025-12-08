package nl.roka.adventofcode.aoc2025.day6;

import static org.assertj.core.api.Assertions.assertThat;

import nl.roka.adventofcode.aoc.puzzle.Answer;
import org.junit.jupiter.api.Test;

class Day6Test {

  @Test
  void silverExample() {
    assertThat(new Day6().runSilver()).isEqualTo(Answer.of(4277556));
  }

  @Test
  void goldExample() {
    assertThat(new Day6().runGold()).isEqualTo(Answer.of(3263827));
  }
}
