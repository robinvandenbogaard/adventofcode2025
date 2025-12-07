package nl.roka.adventofcode.aoc2025.day1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("A lock starting at position 50")
class LockTest {

  public static final int MAX_STEPS = 100;
  public static final int START_POS = 50;
  private final Lock lock = new Lock(START_POS);

  @DisplayName("When turning")
  @Nested
  class Left {

    public static final String DIRECTION = "L";

    public static Stream<Arguments> range0To49() {
      var arguments = new ArrayList<Arguments>();
      for (int steps = 0; steps <= 49; steps++) {
        arguments.add(Arguments.of(steps, START_POS - steps));
      }
      return arguments.stream();
    }

    public static Stream<Arguments> range50To149() {
      var arguments = new ArrayList<Arguments>();
      for (int steps = 50; steps <= 149; steps++) {
        arguments.add(Arguments.of(steps, ((steps % MAX_STEPS) - START_POS) - 100));
      }
      return arguments.stream();
    }

    private Lock whenDialed(int steps) {
      return lock.dial(DIRECTION + steps);
    }

    @DisplayName("Left")
    @ParameterizedTest(name = "{0} times should result in position {1}")
    @MethodSource("range0To49")
    void turnLeft(int steps, int pos) {
      assertThat(whenDialed(steps).position()).isEqualTo(pos);
    }

    @DisplayName("Left")
    @ParameterizedTest(name = "{0} times should not land on zero")
    @MethodSource("range0To49")
    void turnLeftZeroCount(int steps) {
      assertThat(whenDialed(steps).dialPassedZeroCount()).isZero();
      assertThat(whenDialed(steps).dialStoppedOnZeroCount()).isZero();
    }

    @DisplayName("Left")
    @ParameterizedTest(name = "{0} times should stop on zero one time")
    @ValueSource(ints = {50, 150, 250, 350, 450})
    void turnLeftStopAtZero(int steps) {
      assertThat(whenDialed(steps).dialStoppedOnZeroCount()).isOne();
    }

    @DisplayName("Left")
    @ParameterizedTest(name = "{0} times should pass zero one time")
    @MethodSource("range50To149")
    void turnLeftPassZero(int steps) {
      assertThat(whenDialed(steps).dialPassedZeroCount()).isOne();
    }
  }

  @DisplayName("When turning")
  @Nested
  class Right {

    public static final String DIRECTION = "R";

    public static Stream<Arguments> range0To49() {
      var arguments = new ArrayList<Arguments>();
      for (int steps = 0; steps <= 49; steps++) {
        arguments.add(Arguments.of(steps, START_POS + steps));
      }
      return arguments.stream();
    }

    public static Stream<Arguments> range50To149() {
      var arguments = new ArrayList<Arguments>();
      for (int steps = 50; steps <= 149; steps++) {
        arguments.add(Arguments.of(steps, ((steps % MAX_STEPS) - START_POS) - 100));
      }
      return arguments.stream();
    }

    private Lock whenDialed(int steps) {
      return lock.dial(DIRECTION + steps);
    }

    @DisplayName("Right")
    @ParameterizedTest(name = "{0} times should result in position {1}")
    @MethodSource("range0To49")
    void turnLeft(int steps, int pos) {
      assertThat(whenDialed(steps).position()).isEqualTo(pos);
    }

    @DisplayName("Right")
    @ParameterizedTest(name = "{0} times should not land on zero")
    @MethodSource("range0To49")
    void turnLeftZeroCount(int steps) {
      assertThat(whenDialed(steps).dialPassedZeroCount()).isZero();
      assertThat(whenDialed(steps).dialStoppedOnZeroCount()).isZero();
    }

    @DisplayName("Right")
    @ParameterizedTest(name = "{0} times should stop on zero one time")
    @ValueSource(ints = {50, 150, 250, 350, 450})
    void turnLeftStopAtZero(int steps) {
      assertThat(whenDialed(steps).dialStoppedOnZeroCount()).isOne();
    }

    @DisplayName("Right")
    @ParameterizedTest(name = "{0} times should pass zero one time")
    @MethodSource("range50To149")
    void turnLeftPassZero(int steps) {
      assertThat(whenDialed(steps).dialPassedZeroCount()).isOne();
    }
  }

  @Test
  void dial() {
    assertThat(lock.dial("R10").position()).isEqualTo(60);
    assertThat(lock.dial("R100").position()).isEqualTo(50);
    assertThat(lock.dial("L10").position()).isEqualTo(40);
    assertThat(lock.dial("R49").position()).isEqualTo(99);
    assertThat(lock.dial("L50").position()).isEqualTo(0);
    assertThat(lock.dial("L51").position()).isEqualTo(99);
    assertThat(lock.dial("R50").position()).isEqualTo(0);
    assertThat(lock.dial("R100").position()).isEqualTo(50);
    assertThat(lock.dial("R200").position()).isEqualTo(50);
  }

  @Test
  void hitZero() {
    assertThat(lock.dial("R10").dialStoppedOnZeroCount()).isEqualTo(0);
    assertThat(lock.dial("L10").dialStoppedOnZeroCount()).isEqualTo(0);
    assertThat(lock.dial("R49").dialStoppedOnZeroCount()).isEqualTo(0);
    assertThat(lock.dial("L50").dialStoppedOnZeroCount()).isEqualTo(1);
    assertThat(lock.dial("L51").dialStoppedOnZeroCount()).isEqualTo(0);
    assertThat(lock.dial("R50").dialStoppedOnZeroCount()).isEqualTo(1);
    assertThat(lock.dial("R100").dialStoppedOnZeroCount()).isEqualTo(0);
    assertThat(lock.dial("R200").dialStoppedOnZeroCount()).isEqualTo(0);
  }

  @Test
  void pointedZero() {
    assertThat(lock.dial("R10").dialPassedZeroCount()).isEqualTo(0);
    assertThat(lock.dial("L10").dialPassedZeroCount()).isEqualTo(0);
    assertThat(lock.dial("R49").dialPassedZeroCount()).isEqualTo(0);
    assertThat(lock.dial("L50").dialPassedZeroCount()).isEqualTo(1);
    assertThat(lock.dial("L51").dialPassedZeroCount()).isEqualTo(1);
    assertThat(lock.dial("R50").dialPassedZeroCount()).isEqualTo(1);
    assertThat(lock.dial("R100").dialPassedZeroCount()).isEqualTo(1);
    assertThat(lock.dial("R200").dialPassedZeroCount()).isEqualTo(2);
    assertThat(lock.dial("L251").dialPassedZeroCount()).isEqualTo(3);
  }

  @Test
  void smallStepsRight() {
    assertThat(Lock.of(99, 0, 0).dial("R1")).isEqualTo(Lock.of(0, 1, 1));
    assertThat(Lock.of(99, 0, 0).dial("R2")).isEqualTo(Lock.of(1, 0, 1));
  }

  @Test
  void smallStepsLeft() {
    assertThat(Lock.of(5, 0, 0).dial("L5")).isEqualTo(Lock.of(0, 1, 1));
    assertThat(Lock.of(5, 0, 0).dial("L6")).isEqualTo(Lock.of(99, 0, 1));
  }

  @Test
  void zeroStepsDoesNotChangePosition() {
    assertThat(lock.dial("R0").position()).isEqualTo(START_POS);
  }

  @Test
  void cannotDialNegativeSteps() {
    assertThatThrownBy(() -> lock.dial("R-10")).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> lock.dial("L-10")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void zeroStepsDoesNotChangeCountsWhenAlreadyAtZero() {
    assertThat(lock.dial("L50").dial("R0").dialStoppedOnZeroCount()).isEqualTo(1);
    assertThat(lock.dial("L50").dial("R0").dialPassedZeroCount()).isEqualTo(1);
  }

  @Test
  void rotateRight1000Times() {
    assertThat(lock.dial("R1000").position()).isEqualTo(START_POS);
    assertThat(lock.dial("R1000").dialStoppedOnZeroCount()).isZero();
    assertThat(lock.dial("R1000").dialPassedZeroCount()).isEqualTo(10);
  }

  @Test
  void rotateLeft1000Times() {
    assertThat(lock.dial("L1000").position()).isEqualTo(START_POS);
    assertThat(lock.dial("L1000").dialStoppedOnZeroCount()).isZero();
    assertThat(lock.dial("L1000").dialPassedZeroCount()).isEqualTo(10);
  }

  @Test
  void rotateLeftRight1000Times() {
    assertThat(lock.dial("L1000").dial("R1000").position()).isEqualTo(START_POS);
    assertThat(lock.dial("L1000").dial("R1000").dialStoppedOnZeroCount()).isZero();
    assertThat(lock.dial("L1000").dial("R1000").dialPassedZeroCount()).isEqualTo(20);
  }

  @Test
  void clickOffZeroDoesNotIncreasesPass() {
    assertThat(Lock.of(0, 0, 0).dial("L1").dialPassedZeroCount()).isEqualTo(0);
    assertThat(Lock.of(0, 0, 0).dial("R1").dialPassedZeroCount()).isEqualTo(0);
  }

  @Test
  void clickToZeroDoesIncreasesPass() {
    assertThat(Lock.of(1, 0, 0).dial("L1").dialPassedZeroCount()).isEqualTo(1);
    assertThat(Lock.of(99, 0, 0).dial("R1").dialPassedZeroCount()).isEqualTo(1);
  }

  @Test
  void clickToZeroDoesIncreasesLand() {
    assertThat(Lock.of(1, 0, 0).dial("L1").dialStoppedOnZeroCount()).isEqualTo(1);
    assertThat(Lock.of(99, 0, 0).dial("R1").dialStoppedOnZeroCount()).isEqualTo(1);
  }

  @ParameterizedTest
  @MethodSource("day1InputSteps")
  void day1InputSteps_tests(Lock input, String steps, Lock expected) {
    assertThat(input.dial(steps)).as("Dialing %s on %s.", steps, input).isEqualTo(expected);
  }

  public static Stream<Arguments> day1InputSteps() {
    return Stream.of(
        Arguments.of(Lock.of(5, 1118, 6080), "L22", Lock.of(83, 1118, 6081)),
        Arguments.of(Lock.of(5, 1118, 6080), "L5", Lock.of(0, 1119, 6081)),
        Arguments.of(Lock.of(82, 0, 1), "L30", Lock.of(52, 0, 1)),
        Arguments.of(Lock.of(0, 1, 1), "L5", Lock.of(95, 1, 1)));
  }
}
