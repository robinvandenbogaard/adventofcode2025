package nl.roka.adventofcode.aoc.puzzle;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDayPuzzle implements DayPuzzle {
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  protected final Day day;
  private final Solutions solutions;

  public AbstractDayPuzzle(Day day) {
    this.day = Objects.requireNonNull(day);
    this.solutions = Solutions.none();
  }

  public AbstractDayPuzzle(Day day, Solutions solutions) {
    this.day = Objects.requireNonNull(day);
    this.solutions = solutions;
  }

  @Override
  public int dayNumber() {
    return day.number();
  }

  @Override
  public Answer goldVerified() {
    return solutions.gold();
  }

  @Override
  public Answer silverVerified() {
    return solutions.silver();
  }
}
