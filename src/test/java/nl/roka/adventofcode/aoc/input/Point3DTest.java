package nl.roka.adventofcode.aoc.input;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

  @Test
  void distance() {
    var a = new Point3D(7, 4, 3);
    var b = new Point3D(17, 6, 2);

    assertThat(a.distance(b)).isEqualTo(new BigDecimal("10.246951"));
    assertThat(b.distance(a)).isEqualTo(new BigDecimal("10.246951"));
  }
}
