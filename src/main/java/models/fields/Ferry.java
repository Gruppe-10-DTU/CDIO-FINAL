package models.fields;

import models.Player;
import models.dto.IGameStateDTO;

public class Ferry extends Property{

    private int[] rent = new int[4];

    public int[] getRent() {
        return rent;
    }

    public void setRent(int index, int rentAmound) {
        this.rent[index] = rentAmound;
    }


    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier) {
        Player currentPlayer = gameState.getActivePlayer();

        if (owner == null) {
            if (currentPlayer.getBalance() >= price) {
                String msg = "Du er landet på " + name + " Vil du købe den for " + price + "kr";
                boolean wantToBuy = gameState.getGuiController().getUserLeftButtonPressed(msg, "Ja", "Nej");

                if (wantToBuy) {
                    owner = currentPlayer;
                    currentPlayer.setBalance(-price);
                } else {
                    this.auction(gameState);
                }
            } else {
                //Player cant buy (possibly give the player an option to sell other values and then buy?)
                String msg = "Du er landet på " + name + " Til en værdi af " + price + "og har dessværre ikke råd til at købe den";

                gameState.getGuiController().displayMsg(msg);
                this.auction(gameState);
            }
        } else {
            //Pay rent
            int ownerOwnes = gameState.getFieldController().ferrysOwned(owner, iD, 4); //Change to the actual number of ferry fields owned by the player to include rent bonus
            int rentToPay = rent[ownerOwnes-1] * rentMultiplier;

            if (owner == currentPlayer) {
                String msg = "Du er landet på din egen grund";
                gameState.getGuiController().displayMsg(msg);
            } else if (gameState.getFieldController().isJailed(owner)) {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " men da ejeren er i fængselbetales ingen leje ";
                gameState.getGuiController().displayMsg(msg);
            } else if (currentPlayer.setBalance(-rentToPay)) {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " betal leje " + rentToPay;
                gameState.getGuiController().displayMsg(msg);
                owner.setBalance(rentToPay);
                gameState.getGuiController().updatePlayer(owner);
            } else {
                //Cant pay the rent
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " du har ikke råd til at betale lejen";
                gameState.getGuiController().displayMsg(msg);

                //Player must leave the game (later the player will be able to sell things and stay in the game)
                gameState.getPlayerController().removePlayer(gameState.getActivePlayer().getID());
            }


        }
    }
}
