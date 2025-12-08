package nl.roka.adventofcode.aoc2025.day6;

import nl.roka.adventofcode.aoc.input.Grid;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

record MathProblem(MathType type, List<BigInteger> numbers) {

  public static MathProblem addition(Grid numbers) {
    return new MathProblem(MathType.ADD, getValues(numbers));
  }

  public static MathProblem multiplication(Grid numbers) {
    return new MathProblem(MathType.MULTIPLY, getValues(numbers));
  }

  public static MathProblem additionCol(Grid numbers) {
    return new MathProblem(MathType.ADD, getValuesCol(numbers));
  }

  public static MathProblem multiplicationCol(Grid numbers) {
    return new MathProblem(MathType.MULTIPLY, getValuesCol(numbers));
  }

  private static ArrayList<BigInteger> getValues(Grid numbers) {
    var values = new ArrayList<BigInteger>();
    for (var row = 0; row < numbers.height(); row++) {
      values.add(new BigInteger(numbers.row(row).trim()));
    }
    return values;
  }

  private static ArrayList<BigInteger> getValuesCol(Grid numbers) {
    var values = new ArrayList<BigInteger>();
    for (var column = numbers.width() - 1; column >= 0; column--) {
      var trimmed = numbers.column(column).trim();
      if (!trimmed.isBlank()) values.add(new BigInteger(trimmed));
    }
    return values;
  }

  public BigInteger total() {
    return switch (type) {
      case ADD -> addAll();
      case MULTIPLY -> multiplyAll();
    };
  }

  private BigInteger multiplyAll() {
    return numbers.stream().reduce(BigInteger.ONE, BigInteger::multiply);
  }

  private BigInteger addAll() {
    return numbers.stream().reduce(BigInteger.ZERO, BigInteger::add);
  }
}
