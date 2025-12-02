package nl.roka.adventofcode.aoc.runner;

import java.time.Duration;

record StoppedClock(long nanoTimeStart, long nanoTimeStop) {

  public Duration duration() {
    return Duration.ofNanos(nanoTimeStop - nanoTimeStart);
  }
}
