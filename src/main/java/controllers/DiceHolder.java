package controllers;

import models.Die;

import java.util.Arrays;

public class DiceHolder {
    //Private values of class. Not allowed to change values during game outside of this class
    private Die[] dice;
    private int[] rolls;
    private int sameRolls = 0;

    //Default constructor for the game. Takes 2 dice
    public DiceHolder() {
        //Assign values of array
        dice = new Die[]{new Die(), new Die()};
        rolls = new int[2];
    }

    public int[] getRolls(){return rolls;}

    //Constructor with n amount of dice, in case customer wants to change the game
    public DiceHolder(int diceAmt) {
        //Instance dice
        dice = new Die[diceAmt];
        for (int i = 0; i < diceAmt; i++) {
            dice[i] = new Die();
        }
        rolls = new int[diceAmt];
    }

    //Iterate over each die, roll it, and assign the rolls to an array for stats checking.
    public void roll() {
        rollDie();
    }

    //private method for rolling the dice
    private void rollDie() {
        for (int i = 0; i < dice.length; i++) {
            rolls[i] = dice[i].roll();
        }
    }

    //Get total value of last roll
    public int sum(boolean reverse) {
        int totalValue = 0;
        for (int roll : rolls) {
            totalValue += roll;
        }
        if (reverse) {
            totalValue = totalValue * -1;
        }
        return totalValue;
    }

    public int sum() {
        int totalValue = 0;
        for (int roll : rolls) {
            totalValue += roll;
        }

        return totalValue;
    }

    //See if the n die are equal
    public boolean isEqual() {
        return Arrays.stream(rolls).distinct().count() == 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(rolls);
    }

    public int getSameRolls() {
        return sameRolls;
    }

    public void setSameRolls(int sameRolls) {
        this.sameRolls = sameRolls;
    }

    public void incrementSameRolls() {
        this.sameRolls++;
    }
}
