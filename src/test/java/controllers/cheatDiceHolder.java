package controllers;

public class cheatDiceHolder extends DiceHolder{
    int nextRoll;

    public void setNextRoll(int nextRoll) {
        this.nextRoll = nextRoll;
    }

    @Override
    public int sum() {
        return nextRoll;
    }
}
