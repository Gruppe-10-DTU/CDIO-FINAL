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
    void normalDistrbutionRolls(){
        int[] rolls = new int[13];
        double dataSize = 100_000D;
        double[] deviance = {1.05, 0.95};
        double[] normalDistribution = {0.0,0.0,1/36.0,2/36.0,3/36.0,4/36.0,5/36.0,6/36.0,5/36.0,4/36.0,3/36.0,2/36.0,1/36.0};
        for (int i = 0; i < 100_000; i++) {
            diceHolder.roll();
            rolls[diceHolder.sum()]++;
        }
        for (int i = 0; i < rolls.length; i++) {
            double rollFrequency = rolls[i] / dataSize;
            assertTrue(rollFrequency <= normalDistribution[i]*deviance[0]);
            assertTrue(rollFrequency >= normalDistribution[i]*deviance[1]);

        }

        //assertTrue(rolls[1]/dataSize < normalDistribution[1]*deviance[0] && rolls[1]/dataSize > normalDistribution[1]*deviance[1]);
        //assertTrue(rolls[2]/dataSize < normalDistribution[2]*deviance[0] && rolls[2]/dataSize > normalDistribution[2]*deviance[1]);
        //assertTrue(rolls[3]/dataSize < normalDistribution[3]*deviance[0] && rolls[3]/dataSize > normalDistribution[3]*deviance[1]);
        //assertTrue(rolls[4]/dataSize < normalDistribution[4]*deviance[0] && rolls[4]/dataSize > normalDistribution[4]*deviance[1]);
        //assertTrue(rolls[5]/dataSize < normalDistribution[5]*deviance[0] && rolls[5]/dataSize > normalDistribution[5]*deviance[1]);
        //assertTrue(rolls[6]/dataSize < normalDistribution[6]*deviance[0] && rolls[6]/dataSize > normalDistribution[6]*deviance[1]);
        //assertTrue(rolls[7]/dataSize < normalDistribution[7]*deviance[0] && rolls[7]/dataSize > normalDistribution[7]*deviance[1]);
        //assertTrue(rolls[8]/dataSize < normalDistribution[8]*deviance[0] && rolls[8]/dataSize > normalDistribution[8]*deviance[1]);
        //assertTrue(rolls[9]/dataSize < normalDistribution[9]*deviance[0] && rolls[9]/dataSize > normalDistribution[9]*deviance[1]);
        //assertTrue(rolls[10]/dataSize < normalDistribution[10]*deviance[0] && rolls[10]/dataSize > normalDistribution[10]*deviance[1]);
        //assertTrue(rolls[11]/dataSize < normalDistribution[11]*deviance[0] && rolls[11]/dataSize > normalDistribution[11]*deviance[1]);
        //assertTrue(rolls[12]/dataSize < normalDistribution[12]*deviance[0] && rolls[12]/dataSize > normalDistribution[12]*deviance[1]);
    }
}