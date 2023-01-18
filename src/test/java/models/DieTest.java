package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DieTest {
    Die die;
    int[] rollCount;

    @BeforeEach
    void setUp() {
        die = new Die();
        rollCount = new int[7];
    }

    @Test
    @DisplayName("the die rolls within 5% of statistical probability")
    void roll() {
        for (int i = 0; i < 100_000; i++) {
            rollCount[die.roll()]++;
        }
        for (int i = 1; i < rollCount.length; i++) {
            float rollFrequency = rollCount[i] / 100_000F;
            assertTrue(rollFrequency <= 1.05/6.0 && rollFrequency >= 0.95/6.0);
        }
    }
}