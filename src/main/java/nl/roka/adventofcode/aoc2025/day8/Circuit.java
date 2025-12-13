package nl.roka.adventofcode.aoc2025.day8;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashSet;

public record Circuit(HashSet<FusionBox> boxes) implements Comparable<Circuit> {

  public Circuit(FusionBoxPair pair) {
    this(new HashSet<>());
    add(pair);
  }

  public BigInteger size() {
    return BigInteger.valueOf(boxes.size());
  }

  public void add(FusionBoxPair pair) {
    boxes.add(pair.box1());
    boxes.add(pair.box2());
  }

  public boolean containsAnyOf(FusionBoxPair pair) {
    return boxes.contains(pair.box1()) || boxes.contains(pair.box2());
  }

  public Circuit merge(Circuit circuit) {
    var combined = new HashSet<>(boxes);
    combined.addAll(circuit.boxes);
    return new Circuit(combined);
  }

  @Override
  public int compareTo(Circuit o) {
    return Comparator.comparing(Circuit::size).reversed().compare(this, o);
  }
}
