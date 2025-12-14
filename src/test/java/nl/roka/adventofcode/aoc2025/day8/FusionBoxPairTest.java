package nl.roka.adventofcode.aoc2025.day8;

import static org.assertj.core.api.Assertions.assertThat;

import nl.roka.adventofcode.aoc.input.Line;
import org.junit.jupiter.api.Test;

class FusionBoxPairTest {

  @Test
  void multiplyX() {
    assertThat(
            new FusionBoxPair(FusionBox.of(Line.of("2,0,0")), FusionBox.of(Line.of("5,0,0")))
                .multiplyX())
        .isEqualTo(10);
  }
}
