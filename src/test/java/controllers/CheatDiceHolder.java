package controllers;

import java.util.Arrays;

public class CheatDiceHolder extends DiceHolder{
    int[] rolls;
    private int isEqualAmount = -1;

    public void setRolls(int... rolls){
        this.rolls = rolls;
    }

    public void setIsEqualAmount(int isEqualAmount) {
        this.isEqualAmount = isEqualAmount;
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

    @Override
    public boolean isEqual() {
        return 0<this.isEqualAmount--;
    }
}
