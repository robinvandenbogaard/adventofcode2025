package nl.roka.adventofcode.aoc2025.day8;

import nl.roka.adventofcode.aoc.input.Line;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CircuitTest {

  private final FusionBox box1 = FusionBox.of(Line.of("1,1,1"));
  private final FusionBox box2 = FusionBox.of(Line.of("0,0,0"));
  private final FusionBox box3 = FusionBox.of(Line.of("2,2,2"));
  private final FusionBox box4 = FusionBox.of(Line.of("2,3,2"));

  @Test
  void containsAnyOf() {
    Circuit circuit = new Circuit(new FusionBoxPair(box1, box2));
    assertThat(circuit.containsAnyOf(new FusionBoxPair(box1, box2))).isTrue();
    assertThat(circuit.containsAnyOf(new FusionBoxPair(box1, box1))).isTrue();
    assertThat(circuit.containsAnyOf(new FusionBoxPair(box1, box3))).isTrue();
    assertThat(circuit.containsAnyOf(new FusionBoxPair(box2, box2))).isTrue();
    assertThat(circuit.containsAnyOf(new FusionBoxPair(box3, box2))).isTrue();
    assertThat(circuit.containsAnyOf(new FusionBoxPair(box3, box3))).isFalse();
  }

  @Test
  void merge() {
    Circuit circuit1 = new Circuit(new FusionBoxPair(box1, box2));
    Circuit circuit2 = new Circuit(new FusionBoxPair(box1, box3));
    Circuit merged = circuit1.merge(circuit2);
    assertThat(merged.containsAnyOf(new FusionBoxPair(box1, box1))).isTrue();
    assertThat(merged.containsAnyOf(new FusionBoxPair(box2, box2))).isTrue();
    assertThat(merged.containsAnyOf(new FusionBoxPair(box3, box3))).isTrue();
  }

  @Test
  void compareToBiggestFirst() {
    Circuit circuit1 = new Circuit(new HashSet<>(Set.of(box1)));
    Circuit circuit2 = new Circuit(new HashSet<>(Set.of(box1, box2)));
    Circuit circuit3 = new Circuit(new HashSet<>(Set.of(box1, box2, box3)));

    var sorted = Stream.of(circuit1, circuit2, circuit3).sorted().toList();
    assertThat(sorted).containsExactly(circuit3, circuit2, circuit1);
  }
}
