package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Ferry extends Property{
    private int rent0;
    private int rent1;
    private int rent2;
    private int rent3;


    public void setRent0(int rent0) {
        this.rent0 = rent0;
    }

    public void setRent1(int rent1) {
        this.rent1 = rent1;
    }

    public void setRent2(int rent2) {
        this.rent2 = rent2;
    }

    public void setRent3(int rent3) {
        this.rent3 = rent3;
    }


    public int getRent0() {
        return rent0;
    }

    public int getRent1() {
        return rent1;
    }

    public int getRent2() {
        return rent2;
    }

    public int getRent3() {
        return rent3;
    }

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState) {

        if (owner == null) {
            Player currentPlayer = gameState.getActivePlayer();
            if (currentPlayer.getBalance() > price) {
                String msg = "Du er landet på " + name + " Vil du købe den for " + price + "kr";
                boolean wantToBuy = gameState.getGuiController().getUserLeftButtonPressed(msg, "Ja", "Nej");

                if (wantToBuy) {
                    owner = currentPlayer;
                    currentPlayer.setBalance(currentPlayer.getBalance() - price);
                } else {
                    //Auktion
                    return gameState;
                }

                return gameState;
            } else {
                //Player cant buy
                return gameState;
            }
        } else {
            //Pay rent
            return gameState;
        }
    }
}
