package nl.roka.adventofcode.aoc.runner;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.stream.Stream;
import nl.roka.adventofcode.aoc.puzzle.AbstractDayPuzzle;
import org.reflections.Reflections;

class AllPuzzleRunner {

  static void main(String[] args) {

    if (Runner.WARMUP_REPETITIONS > 0)
      System.err.printf(
          "%nWarming up the jvm by solving all puzzles %d times. Then time and solve the puzzle and print the results.%n%n",
          Runner.WARMUP_REPETITIONS);

    getAbstractDayPuzzles()
        .parallel()
        .forEach(
            puzzle -> {
              Runner.warmup(puzzle);
              Runner.run(puzzle);
            });
  }

  private static Stream<AbstractDayPuzzle> getAbstractDayPuzzles() {
    return new Reflections("nl.roka.adventofcode.aoc" + Configuration.ADVENT_YEAR)
        .getSubTypesOf(AbstractDayPuzzle.class).stream()
            .sorted(Comparator.comparing(Class::getSimpleName))
            .filter(AllPuzzleRunner::isStartingPuzzleAllowed)
            .map(
                typeClass -> {
                  try {
                    return typeClass.getConstructor().newInstance();
                  } catch (NoSuchMethodException
                      | InstantiationException
                      | IllegalAccessException
                      | InvocationTargetException e) {
                    throw new RuntimeException(e);
                  }
                });
  }

  private static boolean isStartingPuzzleAllowed(Class<? extends AbstractDayPuzzle> clazz) {
    var isSlow = clazz.isAnnotationPresent(RequireOptimization.class);
    if (isSlow) System.err.printf("Skipping %s because it is SLOW%n%n", clazz.getSimpleName());
    return !isSlow;
  }
}
