package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

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
                }
            } else {
                //Player cant buy (possibly give the player an option to sell other values and then buy?)
                String msg = "Du er landet på " + name + " Til en værdi af " + price + "og har dessværre ikke råd til at købe den";

                gameState.getGuiController().displayMsg(msg);
            }
        } else {
            //Pay rent

        }
        return gameState;
    }
}
