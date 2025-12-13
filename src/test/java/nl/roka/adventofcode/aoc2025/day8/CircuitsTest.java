package nl.roka.adventofcode.aoc2025.day8;

import nl.roka.adventofcode.aoc.input.Line;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CircuitsTest {

  private final FusionBox box1 = FusionBox.of(Line.of("1,1,1"));
  private final FusionBox box2 = FusionBox.of(Line.of("0,0,0"));
  private final FusionBox box3 = FusionBox.of(Line.of("2,2,2"));
  private final FusionBox box4 = FusionBox.of(Line.of("3,3,3"));
  private final FusionBoxPair pair12 = new FusionBoxPair(box1, box2);
  private final FusionBoxPair pair13 = new FusionBoxPair(box1, box3);
  private final FusionBoxPair pair34 = new FusionBoxPair(box3, box4);

  @Test
  void add() {
    var circuits = new Circuits();
    circuits.add(pair12);
    assertThat(circuits.circuits()).containsExactlyInAnyOrder(new Circuit(pair12));
    circuits.add(pair34);
    assertThat(circuits.circuits())
        .containsExactlyInAnyOrder(new Circuit(pair12), new Circuit(pair34));
    circuits.add(pair13);
    assertThat(circuits.circuits())
        .containsExactlyInAnyOrder(new Circuit(new HashSet<>(Set.of(box1, box2, box3, box4))));
  }
}
