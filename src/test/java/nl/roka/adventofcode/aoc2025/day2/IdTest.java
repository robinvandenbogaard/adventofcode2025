package nl.roka.adventofcode.aoc2025.day2;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class IdTest {

  @ParameterizedTest
  @CsvSource({"1,2,3", "10,234,244"})
  void sum(BigInteger a, BigInteger b, BigInteger expected) {
    assertThat(a.add(b)).isEqualTo(expected);
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "12",
        "13",
        "14",
        "100",
        "1188511886",
        "1188511884",
        "222220",
        "446443",
        "38593860"
      })
  void isNotRepeatedTwice(BigInteger value) {
    assertThat(Id.of(value).isNotRepeatedTwice()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"11", "22", "99", "1010", "222222", "446446", "38593859"})
  void isRepeatedTwice(BigInteger value) {
    assertThat(Id.of(value).isRepeatedTwice()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "12",
        "13",
        "14",
        "100",
        "1188511886",
        "1188511884",
        "222220",
        "446443",
        "38593860"
      })
  void isNotRepeatedPattern(BigInteger value) {
    assertThat(Id.of(value).isNotRepeatedPattern()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "11",
        "22",
        "99",
        "111",
        "999",
        "1010",
        "222222",
        "446446",
        "38593859",
        "565656",
        "824824824",
        "2121212121"
      })
  void isRepeatedPattern(BigInteger value) {
    assertThat(Id.of(value).isRepeatedPattern()).isTrue();
  }
}
