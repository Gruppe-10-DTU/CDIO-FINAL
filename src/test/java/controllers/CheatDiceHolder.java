package controllers;

import java.util.Arrays;

public class CheatDiceHolder extends DiceHolder{
    int nextRoll;
    int[] rolls;

    public void setRolls(int... rolls){
        this.rolls = rolls;
    }

    @Override
    public void roll() {

    }

    @Override
    public int[] getRolls() {
        return this.rolls;
    }

    public CheatDiceHolder(){
        super();
        rolls = new int[1];
    }

    public CheatDiceHolder(int diceAmount){
        super(diceAmount);
        rolls = new int[diceAmount];
    }

    @Override
    public int sum() {
        return Arrays.stream(rolls).sum();
    }
}
