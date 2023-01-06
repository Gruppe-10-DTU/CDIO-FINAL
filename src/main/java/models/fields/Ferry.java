package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Ferry extends Property{

    private int[] rent = new int[4];
    private int rent0;
    private int rent1;
    private int rent2;
    private int rent3;

    public int[] getRent() {
        return rent;
    }

    public void setRent(int index, int rentAmound) {
        this.rent[index] = rentAmound;
    }


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
        Player currentPlayer = gameState.getActivePlayer();

        if (owner == null) {
            if (currentPlayer.getBalance() >= price) {
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
            int ownerOwnes = 1; //Change to the actual number of ferry fields owned by the player to include rent bonus
            int rentToPay = rent[ownerOwnes - 1];

            if (currentPlayer.getBalance() >= rentToPay) {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " betal leje " + rentToPay;
                gameState.getGuiController().displayMsg(msg);

                currentPlayer.setBalance(currentPlayer.getBalance() - rentToPay);
                owner.setBalance(owner.getBalance() + rentToPay);
            } else {
                //Cant pay the rent
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " du har ikke råd til at betale lejen";
                gameState.getGuiController().displayMsg(msg);

                //Player must leave the game (later the player will be able to sell things and stay in the game)
            }


        }
        return gameState;
    }
}
