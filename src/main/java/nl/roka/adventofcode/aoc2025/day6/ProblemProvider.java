package nl.roka.adventofcode.aoc2025.day6;

import nl.roka.adventofcode.aoc.input.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class ProblemProvider {
  private final List<MathProblem> problems;

  ProblemProvider(List<MathProblem> problems) {
    this.problems = problems;
  }

  public static ProblemProvider ofRows(Grid grid) {
    var symbols = grid.row(grid.height() - 1);
    final var y1 = 0;
    final var y2 = grid.height() - 2;

    var problems = new ArrayList<MathProblem>();

    var x1 = 0;
    var x2 = 0;
    for (var index = 1; index < symbols.length(); index++) {
      if (symbols.charAt(index) != ' ') {
        x2 = index - 1;
        if (symbols.charAt(x1) == '*')
          problems.add(MathProblem.multiplication(grid.subGrid(x1, y1, x2, y2)));
        else problems.add(MathProblem.addition(grid.subGrid(x1, y1, x2, y2)));
        x1 = index;
      }
    }
    if (symbols.charAt(x1) == '*')
      problems.add(MathProblem.multiplication(grid.subGrid(x1, y1, grid.width() - 1, y2)));
    else problems.add(MathProblem.addition(grid.subGrid(x1, y1, grid.width() - 1, y2)));
    return new ProblemProvider(problems);
  }

  public static ProblemProvider ofColumns(Grid grid) {
    var symbols = grid.row(grid.height() - 1);
    final var y1 = 0;
    final var y2 = grid.height() - 2;

    var problems = new ArrayList<MathProblem>();

    var x1 = 0;
    var x2 = symbols.length() - 1;
    for (var index = symbols.length() - 1; index >= 0; index--) {
      if (symbols.charAt(index) != ' ') {
        x1 = index;
        if (symbols.charAt(x1) == '*')
          problems.add(MathProblem.multiplicationCol(grid.subGrid(x1, y1, x2, y2)));
        else problems.add(MathProblem.additionCol(grid.subGrid(x1, y1, x2, y2)));
        x2 = index - 1;
      }
    }
    return new ProblemProvider(problems);
  }

  public Stream<MathProblem> stream() {
    return problems.stream();
  }

  public List<MathProblem> problems() {
    return problems;
  }
}
