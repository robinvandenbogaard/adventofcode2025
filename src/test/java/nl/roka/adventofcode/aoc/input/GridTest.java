package nl.roka.adventofcode.aoc.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GridTest {

  public static final String AOC_INPUT_GRID_TEST_TXT = "/aoc/input/gridTest.txt";

  @Test
  void gridCreation() {
    Grid grid = Grid.of(LineReader.of(AOC_INPUT_GRID_TEST_TXT));

    assertThat(grid.get(0, 0)).isEqualTo("1");
    assertThat(grid.get(0, 1)).isEqualTo("2");
    assertThat(grid.get(0, 2)).isEqualTo("3");

    assertThat(grid.get(1, 0)).isEqualTo("a");
    assertThat(grid.get(1, 1)).isEqualTo("b");
    assertThat(grid.get(1, 2)).isEqualTo("c");
  }

  @Test
  void outOfBounds() {
    Grid grid = Grid.of(LineReader.of(AOC_INPUT_GRID_TEST_TXT));

    assertThat(grid.get(-1, 0)).isNull();
    assertThat(grid.get(3, 0)).isNull();
    assertThat(grid.get(1, -1)).isNull();
    assertThat(grid.get(1, 3)).isNull();
  }

  @Test
  void symbol() {
    Grid grid = Grid.of(LineReader.of(AOC_INPUT_GRID_TEST_TXT));

    assertThat(grid.findSymbol("b")).isEqualTo(Point.of(1, 1));
  }

  @Test
  void directional() {
    Grid grid = Grid.of(LineReader.of(AOC_INPUT_GRID_TEST_TXT));
    Point point_b = grid.findSymbol("b");

    assertThat(grid.get(point_b.north())).isEqualTo("2");
    assertThat(grid.get(point_b.south())).isEqualTo("#");
    assertThat(grid.get(point_b.west())).isEqualTo("a");
    assertThat(grid.get(point_b.east())).isEqualTo("c");
  }

  @Test
  void setSymbol() {
    Grid grid = Grid.of(LineReader.of(AOC_INPUT_GRID_TEST_TXT));

    grid.set(Point.of(2, 0), "*");

    assertThat(grid.get(Point.of(2, 0))).isEqualTo("*");
  }

  @Test
  void subGrid() {
    Grid grid = Grid.of(LineReader.of(AOC_INPUT_GRID_TEST_TXT));
    assertThat(grid.subGrid(0, 0, 2, 2)).isEqualTo(grid);
    assertThat(grid.subGrid(0, 0, 1, 1)).isEqualTo(new Grid(new String[] {"12", "ab"}));
    assertThat(grid.subGrid(1, 0, 2, 1)).isEqualTo(new Grid(new String[] {"23", "bc"}));
    assertThat(grid.subGrid(0, 1, 1, 2)).isEqualTo(new Grid(new String[] {"ab", "@#"}));
    assertThat(grid.subGrid(1, 1, 2, 2)).isEqualTo(new Grid(new String[] {"bc", "#$"}));
    assertThat(grid.subGrid(1, 0, 2, 2)).isEqualTo(new Grid(new String[] {"23", "bc", "#$"}));
  }
}
