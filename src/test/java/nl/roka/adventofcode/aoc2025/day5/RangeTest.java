package nl.roka.adventofcode.aoc2025.day5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RangeTest {

  @Test
  void validRange() {
    var range = Range.of("1698522-1698528");
    assertThat(range.length()).isEqualTo(new BigInteger("7"));
  }

  @Test
  void minMustBeSmallerThanMax() {
    assertThrows(IllegalArgumentException.class, () -> Range.of("1698528-1698522"));
  }

  @Test
  void cannotStartInputWithZero() {
    assertThrows(IllegalArgumentException.class, () -> Range.of("0-10"));
  }

  @Test
  void iteratorContainsAllIdsInclusively() {
    var range = Range.of("22-24");
    assertThat(range.ids()).containsExactly(Id.of(22), Id.of(23), Id.of(24));
  }

  @ParameterizedTest
  @ValueSource(strings = {"10", "11", "12"})
  void inRange(Id id) {
    var range = Range.of("10-12");
    assertThat(range.inRange(id)).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"9", "13"})
  void notInRange(Id id) {
    var range = Range.of("10-12");
    assertThat(range.inRange(id)).isFalse();
  }

  @ParameterizedTest
  @ValueSource(strings = {"9-10", "12-13", "10-12"})
  void overLaps(Range other) {
    var range = Range.of("10-12");
    assertThat(range.overLaps(other)).isTrue();
    assertThat(other.overLaps(range)).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"8-9", "13-14"})
  void noOverLaps(Range other) {
    var range = Range.of("10-12");
    assertThat(range.overLaps(other)).isFalse();
    assertThat(other.overLaps(range)).isFalse();
  }

  @ParameterizedTest
  @CsvSource({"9-10,9-12", "12-13,10-13", "10-12,10-12"})
  void merges(Range other, Range expected) {
    var range = Range.of("10-12");
    assertThat(range.merge(other)).isEqualTo(expected);
    assertThat(other.merge(range)).isEqualTo(expected);
  }

  @ParameterizedTest
  @ValueSource(strings = {"8-9", "13-14"})
  void merges(Range other) {
    var range = Range.of("10-12");
    assertThatThrownBy(() -> range.merge(other)).isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @CsvSource({"10-10,10-10", "10-11,10-11", "11-12,11-12"})
  void comparingEqual(Range a, Range b) {
    assertThat(a.compareTo(b)).isEqualTo(0);
  }

  @ParameterizedTest
  @CsvSource({"9-10,10-10", "10-10,10-11", "8-40,11-12"})
  void comparingLess(Range a, Range b) {
    assertThat(a.compareTo(b)).isEqualTo(-1);
  }

  @ParameterizedTest
  @CsvSource({"10-10,9-10", "10-11,10-10", "11-12,8-40"})
  void comparingGreater(Range a, Range b) {
    assertThat(a.compareTo(b)).isEqualTo(1);
  }
}
