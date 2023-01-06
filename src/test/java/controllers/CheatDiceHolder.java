package controllers;

public class CheatDiceHolder extends DiceHolder{
    int nextRoll;
    int[] rolls;

    public void setNextRoll(int nextRoll) {
        this.nextRoll = nextRoll;
    }

    public void setRolls(int... rolls){
        this.rolls = rolls;
    }

    @Override
    public int[] getRolls() {
        return super.getRolls();
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
        return nextRoll;
    }
}
