package nl.roka.adventofcode.aoc.runner;

import nl.roka.adventofcode.aoc.puzzle.DayPuzzle;

public class Runner {

  static final int WARMUP_REPETITIONS = 5;

  public static void run(DayPuzzle puzzle) {

    var silver = runSilver(puzzle);
    var gold = runGold(puzzle);
    var run = new PuzzleRun(puzzle.dayNumber(), silver, gold);

    PuzzleRunPrinter.print(run);
    PuzzleStorage.save(run);
  }

  public static void warmup(DayPuzzle puzzle) {
    for (int repetition = 0; repetition < WARMUP_REPETITIONS; repetition++) {
      puzzle.runSilver();
      puzzle.runGold();
    }
  }

  private static MedalRun runSilver(DayPuzzle puzzle) {
    var clock = Clock.start();
    var silver = puzzle.runSilver();
    var silverClock = clock.stop();
    return new MedalRun(silverClock, silver, puzzle.silverVerified());
  }

  private static MedalRun runGold(DayPuzzle puzzle) {
    var clock = Clock.start();
    var gold = puzzle.runGold();
    var goldClock = clock.stop();
    return new MedalRun(goldClock, gold, puzzle.goldVerified());
  }
}
