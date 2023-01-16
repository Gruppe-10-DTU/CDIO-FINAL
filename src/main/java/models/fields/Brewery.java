package models.fields;

import models.Player;
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
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier) {
        Player currentPlayer = gameState.getActivePlayer();

        if (owner == null) {

            if (currentPlayer.getBalance() > price) {
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
                String msg = "Du er landet på " + name + " Til en værdi af " + price + "og har desværre ikke råd til at købe den";

                gameState.getGuiController().displayMsg(msg);
                this.auction(gameState);
            }
        } else {
            //Pay rent
            int diceAmount = gameState.getDiceHolder().sum();
            int ownerPropertyAmount = gameState.getFieldController().breweriesOwned(owner, iD);
            int rentToPay;


            if (ownerPropertyAmount == 2) {
                rentToPay = rent1 * diceAmount;
            } else {
                rentToPay = rent0 * diceAmount;
            }

            if (owner == currentPlayer) {
                String msg = "Du er landet på din egen grund";
                gameState.getGuiController().displayMsg(msg);
            } else if (gameState.getFieldController().isJailed(owner)) {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " men da ejeren er i fængsel betales ingen leje ";
                gameState.getGuiController().displayMsg(msg);
            } else if (currentPlayer.getBalance() >= rentToPay) {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " betal leje " + rentToPay;
                gameState.getGuiController().displayMsg(msg);

                currentPlayer.setBalance(-rentToPay);
                owner.setBalance(rentToPay);
                gameState.getGuiController().updatePlayer(owner);
            } else {
                //Cant pay the rent
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " du har ikke råd til at betale lejen";
                gameState.getGuiController().displayMsg(msg);

                //Cant pay the rent
                while (currentPlayer.getBalance() < rentToPay) {
                    if (gameState.getFieldController().countHouse(gameState.getFieldController().ownsColourGroup(currentPlayer)) == 0) {
                        gameState.getGuiController().displayMsg("You cannot pay the rent, and therefore you are disqualified from the game.");
                        owner.setBalance(currentPlayer.getBalance());
                        currentPlayer.setBalance(-currentPlayer.getBalance());
                        gameState.getGuiController().updatePlayer(currentPlayer);
                        gameState.getGuiController().updatePlayer(owner);
                        gameState.getPlayerController().removePlayer(currentPlayer.getID());
                        break;
                    } else {
                        String colorChosen = gameState.getGuiController().selectColorBuild("Choose where you want to sell buildings from", gameState.getFieldController().ownsColourGroup(currentPlayer).keySet().toArray(String[]::new));
                        String whereToSell = gameState.getGuiController().selectBuild("Sell building. 1 house sells for: " + gameState.getFieldController().ownsColourGroup(currentPlayer).get(colorChosen)[0].getHousePrice() / 2 + "", gameState.getFieldController().checkSell(gameState.getFieldController().ownsColourGroup(currentPlayer)).get(colorChosen));
                        if (gameState.getFieldController().getStreetFromString(whereToSell).isHotel()) {
                            gameState.getFieldController().sellBuilding(gameState.getFieldController().getStreetFromString(whereToSell), 0);
                            gameState.getGuiController().guiRemoveHotel(gameState.getFieldController().getStreetFromString(whereToSell));
                            gameState.getGuiController().updatePlayer(currentPlayer);
                        } else if (gameState.getFieldController().getStreetFromString(whereToSell).getHouseAmount() >= 1) {
                            int maxHouse = gameState.getFieldController().getStreetFromString(whereToSell).getHouseAmount();
                            gameState.getFieldController().sellBuilding(gameState.getFieldController().getStreetFromString(whereToSell), gameState.getGuiController().sellAmount(0, maxHouse));
                            gameState.getGuiController().guiAddHouse(gameState.getFieldController().getStreetFromString(whereToSell), (maxHouse - gameState.getFieldController().getStreetFromString(whereToSell).getHouseAmount()));
                            gameState.getGuiController().updatePlayer(currentPlayer);
                        }
                    }
                }
            }

        }
    }
}
