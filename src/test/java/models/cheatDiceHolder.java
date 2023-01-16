package models;

import controllers.DiceHolder;
public class cheatDiceHolder extends DiceHolder {
    public int sum = 0;

    public void setSum(int sum) {
        this.sum = sum;
    }


    @Override
    public int sum(){
        return this.sum;
    }

}