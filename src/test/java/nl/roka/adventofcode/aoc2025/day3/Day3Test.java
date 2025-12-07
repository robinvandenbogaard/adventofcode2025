package nl.roka.adventofcode.aoc2025.day3;

import static org.assertj.core.api.Assertions.assertThat;

import nl.roka.adventofcode.aoc.puzzle.Answer;
import nl.roka.adventofcode.aoc2025.day2.Day2;
import org.junit.jupiter.api.Test;

class Day3Test {

  @Test
  void silverExample() {
    assertThat(new Day3().runSilver()).isEqualTo(Answer.of(357));
  }

  @Test
  void goldExample() {
    assertThat(new Day3().runGold()).isEqualTo(Answer.of("3121910778619"));
  }
}
