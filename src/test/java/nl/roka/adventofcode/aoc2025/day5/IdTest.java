package nl.roka.adventofcode.aoc2025.day5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IdTest {

  @ParameterizedTest
  @CsvSource({"1,2,3", "10,234,244"})
  void sum(Id a, Id b, Id expected) {
    assertThat(a.add(b)).isEqualTo(expected);
  }
}
