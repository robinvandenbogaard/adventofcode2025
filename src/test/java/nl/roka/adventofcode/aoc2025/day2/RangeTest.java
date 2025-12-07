package nl.roka.adventofcode.aoc2025.day2;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  void sumOfInvalidIds() {
    var range = Range.of("22-33");
    assertThat(range.sumIdsRepeatedTwice())
        .isEqualTo(new BigInteger("22").add(new BigInteger("33")));
  }
}
