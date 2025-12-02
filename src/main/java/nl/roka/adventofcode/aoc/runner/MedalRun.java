package nl.roka.adventofcode.aoc.runner;

import java.time.Duration;
import nl.roka.adventofcode.aoc.puzzle.Answer;

record MedalRun(StoppedClock clock, Answer runAnswer, Answer verifiedAnswer) {
  public Duration duration() {
    return clock.duration();
  }

  public String answer() {
    return runAnswer.text();
  }

  public String verified() {
    return verifiedAnswer.text();
  }

  public boolean matchesVerified() {
    return runAnswer.equals(verifiedAnswer);
  }
}
