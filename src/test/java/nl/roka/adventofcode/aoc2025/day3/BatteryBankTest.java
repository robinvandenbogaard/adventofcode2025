package nl.roka.adventofcode.aoc2025.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import nl.roka.adventofcode.aoc.input.Line;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BatteryBankTest {

  @ParameterizedTest
  @CsvSource({
    "11, 11",
    "182, 82",
    "321,32",
    "987654321111111,98",
    "811111111111119,89",
    "234234234234278,78",
    "818181911112111,92"
  })
  void maxJoltage(Line battery, BigInteger expected) {
    assertThat(BatteryBank.of(battery).maxJoltage()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
    "987654321111111,987654321111",
    "811111111111119,811111111119",
    "234234234234278,434234234278",
    "818181911112111,888911112111"
  })
  void maxAmpedJoltage(Line battery, BigInteger expected) {
    assertThat(BatteryBank.of(battery).maxAmpedJoltage()).isEqualTo(expected);
  }

  @Test
  void resetOthers() {
    var bank = new BatteryBank("8413", 2);

    bank.resetOthers(0, 0);
    assertThat(BatteryBank.slotMax[0]).isEqualTo(8);
    assertThat(BatteryBank.slotMax[1]).isEqualTo(4);

    bank.resetOthers(0, 1);
    assertThat(BatteryBank.slotMax[0]).isEqualTo(4);
    assertThat(BatteryBank.slotMax[1]).isEqualTo(1);

    bank.resetOthers(0, 0);
    bank.resetOthers(1, 2);
    assertThat(BatteryBank.slotMax[0]).isEqualTo(8);
    assertThat(BatteryBank.slotMax[1]).isEqualTo(1);

    bank.resetOthers(0, 0);
    bank.resetOthers(1, 3);
    assertThat(BatteryBank.slotMax[0]).isEqualTo(8);
    assertThat(BatteryBank.slotMax[1]).isEqualTo(3);
  }
}
