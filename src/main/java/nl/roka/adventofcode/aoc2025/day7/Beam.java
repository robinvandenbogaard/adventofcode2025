package nl.roka.adventofcode.aoc2025.day7;

import java.util.Objects;
import java.util.Set;
import nl.roka.adventofcode.aoc.input.Grid;
import nl.roka.adventofcode.aoc.input.Point;

public class Beam {
  private final Grid grid;
  private Point position;
  private boolean split = false;
  private boolean destroyed = false;

  public Beam(Grid grid, Point startPoint) {
    this.grid = grid;
    this.position = startPoint;
  }

  public Set<Beam> moveDown() {
    position = this.position.south();
    if (!grid.inBounds(position)) {
      destroyed = true;
    } else if (grid.get(position).equals("^")) {
      split = true;
      destroyed = true;
      grid.set(position.west(), "|");
      grid.set(position.east(), "|");
      return Set.of(new Beam(grid, position.west()), new Beam(grid, position.east()));
    } else {
      grid.set(position, "|");
    }
    return Set.of();
  }

  public boolean split() {
    return split;
  }

  public boolean destroyed() {
    return destroyed;
  }

  @Override
  public String toString() {
    return "Beam[" + "position=" + position + ']';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Beam beam = (Beam) o;
    return Objects.equals(position, beam.position);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(position);
  }
}
