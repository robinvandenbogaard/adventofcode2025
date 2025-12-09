package nl.roka.adventofcode.aoc2025.day5;

import java.util.ArrayList;
import java.util.List;

record RangeMerger(List<IdRange> ranges) {
  public List<IdRange> merged() {
    if (ranges.size() <= 1) {
      return ranges;
    }
    var result = new ArrayList<IdRange>();
    var sorted = ranges.stream().sorted().toList();
    var previous = sorted.getFirst();
    for (int i = 1; i < sorted.size(); i++) {
      var current = sorted.get(i);
      if (previous.overLaps(current)) {
        previous = previous.merge(current);
      } else {
        result.add(previous);
        previous = current;
      }
    }
    // always add the last one
    result.add(previous);

    return result;
  }
}
