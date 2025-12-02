package nl.roka.adventofcode.aoc.puzzle;

public interface DayPuzzle {
  Answer runSilver();

  Answer runGold();

  int dayNumber();

  Answer goldVerified();

  Answer silverVerified();
}
