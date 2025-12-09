package nl.roka.adventofcode.aoc2025.day5;

import nl.roka.adventofcode.aoc.util.Range;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

record IdRange(Range range) implements Comparable<IdRange> {
  IdRange(BigInteger min, BigInteger max) {
    this(new Range(min, max));
  }

  public static IdRange of(String input) {
    var split = input.split("-");

    return new IdRange(new BigInteger(split[0]), new BigInteger(split[1]));
  }

  public BigInteger length() {
    return range.length();
  }

  public Stream<Id> ids() {
    var ids = new ArrayList<Id>();
    for (BigInteger i = range.min(); i.compareTo(range.max()) <= 0; i = i.add(BigInteger.ONE)) {
      ids.add(Id.of(i));
    }
    return ids.stream();
  }

  public boolean inRange(Id id) {
    return range.inRange(id.value());
  }

  public boolean overLaps(IdRange other) {
    return range.overLaps(other.range);
  }

  public IdRange merge(IdRange other) {
    return new IdRange(range.merge(other.range));
  }

  @Override
  public int compareTo(IdRange other) {
    return Comparator.comparing(IdRange::range).compare(this, other);
  }
}
