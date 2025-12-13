package nl.roka.adventofcode.aoc2025.day8;

import java.util.ArrayList;
import java.util.List;

public class AllBoxes {
  private final List<FusionBox> boxes;

  public AllBoxes(List<FusionBox> uniqueBoxes) {
    if (uniqueBoxes.size() % 2 != 0) {
      throw new IllegalArgumentException("Must have an even number of boxes");
    }
    this.boxes = uniqueBoxes;
  }

  public List<FusionBoxPair> sortByDistance() {
    var pairs = new ArrayList<FusionBoxPair>();
    for (var i = 0; i < boxes.size(); i++) {
      for (var j = i + 1; j < boxes.size(); j++) {
        pairs.add(new FusionBoxPair(boxes.get(i), boxes.get(j)));
      }
    }

    return pairs.stream().sorted().toList();
  }
}
