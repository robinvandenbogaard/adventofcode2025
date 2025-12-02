package nl.roka.adventofcode.aoc.puzzle;

public record Solutions(Answer silver, Answer gold) {

  public static Solutions of() {
    return new Solutions(Answer.TBD, Answer.TBD);
  }

  public static Solutions none() {
    return new Solutions(Answer.TBD, Answer.TBD);
  }

  public static Solutions silver(int silver) {
    return new Solutions(Answer.of(silver), Answer.TBD);
  }

  public static Solutions silver(String silver) {
    return new Solutions(Answer.of(silver), Answer.TBD);
  }

  public static Solutions of(int silver) {
    return new Solutions(Answer.of(silver), Answer.TBD);
  }

  public static Solutions of(String silver) {
    return new Solutions(Answer.of(silver), Answer.TBD);
  }

  public static Solutions of(int silver, int gold) {
    return new Solutions(Answer.of(silver), Answer.of(gold));
  }

  public static Solutions of(String silver, String gold) {
    return new Solutions(Answer.of(silver), Answer.of(gold));
  }
}
