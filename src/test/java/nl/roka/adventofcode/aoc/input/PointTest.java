package nl.roka.adventofcode.aoc.input;

import static nl.roka.adventofcode.aoc.input.Point.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PointTest {

  @Test
  void adjecent() {
    assertThat(Point.of(0, 0).adjecent())
        .containsExactlyInAnyOrder(
            Point.of(-1, -1),
            Point.of(0, -1),
            Point.of(1, -1),
            Point.of(-1, 0),
            Point.of(1, 0),
            Point.of(-1, 1),
            Point.of(0, 1),
            Point.of(1, 1));
  }

  @Test
  void directions() {
    assertThat(NORTH).isEqualTo(Point.of(-1, 0));
    assertThat(SOUTH).isEqualTo(Point.of(1, 0));
    assertThat(WEST).isEqualTo(Point.of(0, -1));
    assertThat(EAST).isEqualTo(Point.of(0, 1));
    assertThat(NORTHEAST).isEqualTo(Point.of(-1, 1));
    assertThat(SOUTHWEST).isEqualTo(Point.of(1, -1));
    assertThat(NORTHWEST).isEqualTo(Point.of(-1, -1));
    assertThat(SOUTHEAST).isEqualTo(Point.of(1, 1));
  }

  @Test
  void distance() {
    assertThat(ZERO.distance(Point.of(-3, -3))).isEqualTo(Point.of(-3, -3));
    assertThat(Point.of(10, -10).distance(Point.of(5, -6))).isEqualTo(Point.of(-5, 4));
  }
}
