package nl.roka.adventofcode.aoc2025.day2;

import static org.assertj.core.api.Assertions.assertThat;

import nl.roka.adventofcode.aoc.puzzle.Answer;
import org.junit.jupiter.api.Test;

class Day2Test {

  @Test
  void silverExample() {
    assertThat(new Day2().runSilver()).isEqualTo(Answer.of(1227775554));
  }

  @Test
  void goldExample() {
    assertThat(new Day2().runGold()).isEqualTo(Answer.of("4174379265"));
  }
}
