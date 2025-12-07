package nl.roka.adventofcode.aoc.puzzle;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import nl.roka.adventofcode.aoc.input.Grid;
import nl.roka.adventofcode.aoc.input.Line;
import nl.roka.adventofcode.aoc.input.LineReader;

public class Day implements Iterable<String> {
  private final LineReader reader;
  private final int number;

  public Day(int number) {
    this(number, LineReader.of("/day%d.in".formatted(number)));
  }

  public Day(int number, LineReader reader) {
    this.reader = reader;
    this.number = number;
  }

  public void forEach(Consumer<? super String> action) {
    reader.reset();
    Objects.requireNonNull(action);
    var line = reader.nextLine();
    while (!Objects.equals(line, Line.none())) {
      action.accept(line.text());
      line = reader.nextLine();
    }
  }

  @Override
  public Iterator<String> iterator() {
    return reader.stream().map(Line::text).iterator();
  }

  @Override
  public Spliterator<String> spliterator() {
    return reader.stream().map(Line::text).spliterator();
  }

  public Stream<Line> stream() {
    reader.reset();
    return reader.stream();
  }

  public int number() {
    return number;
  }

  public Grid fullGrid() {
    reader.reset();
    return Grid.of(reader);
  }

  public Grid fullGrid(String symbol) {
    reader.reset();
    return Grid.withBorder(reader, symbol);
  }

  public <R> Stream<R> map(Function<Line, ? extends R> mapper) {
    return stream().map(mapper);
  }
}
