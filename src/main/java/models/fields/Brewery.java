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
        GameStateDTO newGameState = gameState;

        if (owner == null) {
            Player currentPlayer = gameState.getActivePlayer();
            if (currentPlayer.getBalance() > price) {
                gameState.getGuiController().displayMsg("Du er landet på " + name + " Vil du købe den for " + price + "kr");

                //Bruger interaktion

                owner = currentPlayer;
                newGameState.getActivePlayer().setBalance(currentPlayer.getBalance() - price);

                return newGameState;
            } else {
                //Player cant buy
                return newGameState;
            }
        } else {
            //Pay rent
            return newGameState;
        }
    }
}
