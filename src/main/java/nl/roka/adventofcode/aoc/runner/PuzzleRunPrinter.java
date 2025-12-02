package nl.roka.adventofcode.aoc.runner;

import java.time.Duration;

class PuzzleRunPrinter {
  static final int MAX_FILLER_LENGTH = 2;

  static void print(PuzzleRun run) {
    MedalRun silver = run.silver();
    MedalRun gold = run.gold();
    var result =
        """
      Day %s:  [%s]\tsilver = '%s'  |%s| %s
                [%s]\tgold   = '%s'  |%s| %s"""
            .formatted(
                format(run.dayNumber()),
                humanReadableFormat(silver.duration()),
                silver.answer(),
                silver.matchesVerified() ? "\u2705" : "\u274c",
                silver.matchesVerified() ? "" : " should be " + silver.verified(),
                humanReadableFormat(gold.duration()),
                gold.answer(),
                gold.matchesVerified() ? "\u2705" : "\u274c",
                gold.matchesVerified() ? "" : " should be " + gold.verified());
    System.out.println(result);
  }

  private static String format(int day) {
    return leadWithZeros(String.valueOf(day));
  }

  private static String leadWithZeros(String text) {
    if (text.length() > MAX_FILLER_LENGTH) {
      return text;
    } else {
      var missingCharacters = MAX_FILLER_LENGTH - text.length() + 1;
      return "0".repeat(missingCharacters) + text;
    }
  }

  public static String humanReadableFormat(Duration duration) {
    return duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
  }
}
