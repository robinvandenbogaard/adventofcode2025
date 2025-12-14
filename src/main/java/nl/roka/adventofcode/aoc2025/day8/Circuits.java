package nl.roka.adventofcode.aoc2025.day8;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Circuits {
  private final List<Circuit> circuits;

  public Circuits() {
    circuits = new ArrayList<>();
  }

  public void add(FusionBoxPair pair) {
    var matched = circuits.stream().filter(circuit -> circuit.containsAnyOf(pair)).toList();

    if (matched.size() > 1) {
      circuits.removeAll(matched);
      circuits.add(matched.stream().reduce(Circuit::merge).orElseThrow());
    } else if (matched.size() == 1) {
      matched.getFirst().add(pair);
    } else {
      circuits.add(new Circuit(pair));
    }
  }

  public Stream<Circuit> stream() {
    return circuits.stream();
  }

  public List<Circuit> circuits() {
    return new ArrayList<>(circuits);
  }

  public long boxCount() {
    return circuits.stream()
        .map(Circuit::size)
        .reduce(BigInteger.ZERO, BigInteger::add)
        .longValue();
  }
}
