package controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceHolderTest {
    DiceHolder diceHolder;

    @BeforeEach
    void setUp() {
        diceHolder = new DiceHolder();
    }
    @Test
    @DisplayName("rolls follow normal distribution within 5%")
    void normalDistrbutionRolls() {
        int[] rolls = new int[13];
        double dataSize = 100_000D;
        double[] deviance = {1.05, 0.95};
        double[] normalDistribution = {0.0, 0.0, 1 / 36.0, 2 / 36.0, 3 / 36.0, 4 / 36.0, 5 / 36.0, 6 / 36.0, 5 / 36.0, 4 / 36.0, 3 / 36.0, 2 / 36.0, 1 / 36.0};
        for (int i = 0; i < 100_000; i++) {
            diceHolder.roll();
            rolls[diceHolder.sum()]++;
        }
        for (int i = 0; i < rolls.length; i++) {
            double rollFrequency = rolls[i] / dataSize;
            assertTrue(rollFrequency <= normalDistribution[i] * deviance[0]);
            assertTrue(rollFrequency >= normalDistribution[i] * deviance[1]);

        }
    }
}