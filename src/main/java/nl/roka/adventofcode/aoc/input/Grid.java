package nl.roka.adventofcode.aoc.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Grid {
  private final String[] grid;

  Grid(String[] grid) {
    this.grid = grid;
  }

  public static Grid of(LineReader reader) {
    var rowCount = reader.lineCount();
    reader.reset();

    String[] grid = new String[rowCount];

    for (int row = 0; row < rowCount; row++) {
      Line line = reader.nextLine();
      grid[row] = line.text();
    }

    return new Grid(grid);
  }

  public static Grid withBorder(LineReader reader, String symbol) {
    var rowCount = reader.lineCount() + 2;
    reader.reset();

    String[] grid = new String[rowCount];

    var lineLength = 0;
    for (int row = 1; row < rowCount - 1; row++) {
      Line line = reader.nextLine();
      grid[row] = symbol + line.text() + symbol;
      lineLength = line.text().length() + 2;
    }
    var line = symbol.repeat(lineLength);
    grid[0] = line;
    grid[rowCount - 1] = line;

    return new Grid(grid);
  }

  public String get(int row, int column) {
    if (!inBounds(row, column)) return null;
    return String.valueOf(grid[row].toCharArray()[column]);
  }

  public boolean inBounds(int row, int column) {
    return row >= 0 && row < height() && column >= 0 && column < width();
  }

  public int width() {
    return grid[0].length();
  }

  public int height() {
    return grid.length;
  }

  public String row(int row) {
    return grid[row];
  }

  public String get(Point point) {
    return get(point.x(), point.y());
  }

  public Point findSymbol(String symbol) {
    Point result = null;
    for (int row = 0; row < grid.length && result == null; row++) {
      var column = grid[row].indexOf(symbol);
      if (column > -1) result = Point.of(row, column);
    }
    return result;
  }

  public void set(Point point, String symbol) {
    String text = grid[point.x()];
    int index = point.y();
    grid[point.x()] = text.substring(0, index) + symbol + text.substring(index + 1);
  }

  public List<String> columnAsList(int i) {
    return null;
  }

  public char getChar(Point point) {
    return get(point).charAt(0);
  }

  public char getChar(int x, int y) {
    return get(x, y).charAt(0);
  }

  public void print() {
    for (var x = 0; x < width(); x++) {
      for (var y = 0; y < height(); y++) {
        System.out.print(get(x, y));
      }
      System.out.println();
    }
  }

  public boolean inBounds(Point point) {
    return inBounds(point.x(), point.y());
  }

  public Stream<Point> points() {
    var result = new ArrayList<Point>();
    for (var column = 0; column < width(); column++) {
      for (var row = 0; row < height(); row++) {
        result.add(Point.of(row, column));
      }
    }
    return result.stream();
  }

  public Grid subGrid(int x1, int y1, int x2, int y2) {
    var rows = new String[y2 - y1 + 1];
    for (var i = y1; i <= y2; i++) {
      rows[i - y1] = row(i).substring(x1, x2 + 1);
    }
    return new Grid(rows);
  }

  @Override
  public String toString() {
    return "Grid{" + "grid=" + Arrays.toString(grid) + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Grid grid1 = (Grid) o;
    return Objects.deepEquals(grid, grid1.grid);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(grid);
  }
}
