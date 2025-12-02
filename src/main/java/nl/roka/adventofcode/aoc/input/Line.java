package nl.roka.adventofcode.aoc.input;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Line {
  private final String text;

  private Line(String text) {
    this.text = text;
  }

  public static Line of(String text) {
    return new Line(Objects.requireNonNull(text));
  }

  public static Line none() {
    return new Line(null);
  }

  public List<Integer> toIntegers(String delimiter) {
    return Arrays.stream(text.split(delimiter)).map(Integer::valueOf).toList();
  }

  public String text() {
    return text;
  }

  public int lenght() {
    return text == null ? 0 : text.length();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Line line = (Line) o;

    return Objects.equals(text, line.text);
  }

  @Override
  public int hashCode() {
    return text != null ? text.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Line{" + "text='" + text + '\'' + '}';
  }

  public boolean contains(String text) {
    return text().contains(text);
  }
}
