package models.fields;

import models.Player;
import models.dto.IGameStateDTO;

public class Ferry extends Property{

    private int[] rent = new int[4];

    public int[] getRent() {
        return rent;
    }

    public void setRent(int index, int rentAmount) {
        this.rent[index] = rentAmount;
    }

    @Override
    protected int getRentAmount(IGameStateDTO gameState) {
        int ownerOwns = gameState.getFieldController().ferrysOwned(owner, iD, 4); //Change to the actual number of ferry fields owned by the player to include rent bonus
        return rent[ownerOwns-1];
    }
}
