package models.fields;

import models.dto.IGameStateDTO;


public class Brewery extends Property{
    private int rent0;
    private int rent1;


    public void setRent0(int rent0) {
        this.rent0 = rent0;
    }

    public void setRent1(int rent1) {
        this.rent1 = rent1;
    }

    public int getRent0() {
        return rent0;
    }

    public int getRent1() {
        return rent1;
    }

    @Override
    public int getRentAmount(IGameStateDTO gameState) {
        int diceAmount = gameState.getDiceHolder().sum();
        int ownerPropertyAmount = gameState.getFieldController().breweriesOwned(owner, iD);

        if (ownerPropertyAmount == 2) {
            return rent1 * diceAmount;
        } else {
            return rent0 * diceAmount;
        }
    }
}
