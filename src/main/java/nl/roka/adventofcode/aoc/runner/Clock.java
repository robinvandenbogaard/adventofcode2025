package nl.roka.adventofcode.aoc.runner;

class Clock {
  private final long nanoTimeStart;

  private Clock(long nanoTimeStart) {
    this.nanoTimeStart = nanoTimeStart;
  }

  static Clock start() {
    return new Clock(System.nanoTime());
  }

  StoppedClock stop() {
    return new StoppedClock(nanoTimeStart, System.nanoTime());
  }
}
