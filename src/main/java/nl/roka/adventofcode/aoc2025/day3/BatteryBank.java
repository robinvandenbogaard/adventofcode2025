package nl.roka.adventofcode.aoc2025.day3;

import java.math.BigInteger;
import nl.roka.adventofcode.aoc.input.Line;

public record BatteryBank(String value, int slots) {

  static int[] slotMax;

  public BatteryBank {
    slotMax = new int[slots];
    for (int i = 0; i < slots; i++) {
      slotMax[i] = 1;
    }
  }

  public static BatteryBank of(Line line) {
    return new BatteryBank(line.text(), 2);
  }

  /** 123456789 ^^ 0 [12] */
  public BigInteger maxJoltage() {
    resetOthers(0, 0);
    for (var position = 0; position < value.length() - slots; position++) {
      for (var slot = 0; slot < slots; slot++) {
        var nextPosition = Math.min(position + slot + 1, value.length() - 1);
        if (slotMax[slot] < get(nextPosition)) {
          slotMax[slot] = get(nextPosition);
          resetOthers(slot + 1, nextPosition + 1);
          break;
        }
      }
    }

    var result = new StringBuilder();
    for (var slot = 0; slot < slots; slot++) {
      result.append(slotMax[slot]);
    }

    return new BigInteger(result.toString());
  }

  void resetOthers(int startSlot, int position) {
    for (var slot = startSlot; slot < slots; slot++) {
      var nextPosition = Math.min(value.length() - 1, position + (slot - startSlot));

      slotMax[slot] = get(nextPosition);
    }
  }

  public BigInteger maxAmpedJoltage() {
    return new BatteryBank(value, 12).maxJoltage();
  }

  private int get(int index) {
    return Integer.parseInt("" + value.charAt(index));
  }
}
