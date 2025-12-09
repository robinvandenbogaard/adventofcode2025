package nl.roka.adventofcode.aoc2025.day5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RangeMergerTest {
  @Test
  void nothingToMerge() {
    var rangeMerger = new RangeMerger(List.of());
    assertThat(rangeMerger.merged()).isEmpty();
  }

  @Test
  void cannotMergeOneRange() {
    var rangeMerger = new RangeMerger(List.of(IdRange.of("1-2")));
    assertThat(rangeMerger.merged()).containsExactly(IdRange.of("1-2"));
  }

  @Test
  void mergeOverLappingRanges() {
    var rangeMerger = new RangeMerger(List.of(IdRange.of("2-3"), IdRange.of("1-2")));
    assertThat(rangeMerger.merged()).containsExactly(IdRange.of("1-3"));
  }

  @Test
  void mergeOverLappingRangesWithGaps() {
    var rangeMerger =
        new RangeMerger(
            List.of(IdRange.of("2-3"), IdRange.of("1-2"), IdRange.of("8-9"), IdRange.of("3-7")));
    assertThat(rangeMerger.merged()).containsExactly(IdRange.of("1-7"), IdRange.of("8-9"));
  }
}
