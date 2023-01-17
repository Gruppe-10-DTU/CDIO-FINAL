package models.fields;

import models.Player;
import models.dto.IGameStateDTO;
import models.Language;


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
                String msg = Language.getInstance().getLanguageValue(Language.getInstance().getLanguageValue("landOnField", name) + price);
                boolean wantToBuy = gameState.getGuiController().getUserLeftButtonPressed(msg, Language.getInstance().getLanguageValue("ja"), Language.getInstance().getLanguageValue("nej"));

                if (wantToBuy) {
                    owner = currentPlayer;
                    currentPlayer.setBalance(-price);
                } else {
                    this.auction(gameState);
                }
            } else {
                //Player cant buy (possibly give the player an option to sell other values and then buy?)
                String msg = Language.getInstance().getLanguageValue("landOnFieldsNoFunds",name);

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
                String msg = Language.getInstance().getLanguageValue("ownField");
                gameState.getGuiController().displayMsg(msg);
            } else if (gameState.getFieldController().isJailed(owner)) {
                String msg = Language.getInstance().getLanguageValue("landOnFieldOwnerJailed", name, owner.getIdentifier());
                gameState.getGuiController().displayMsg(msg);
            } else if (currentPlayer.getBalance() >= rentToPay) {
                String msg = Language.getInstance().getLanguageValue("landOnFieldOwned", name, owner.getIdentifier(), "" + rentToPay);
                gameState.getGuiController().displayMsg(msg);

                currentPlayer.setBalance(-rentToPay);
                owner.setBalance(rentToPay);
                gameState.getGuiController().updatePlayer(owner);
            } else {
                //Cant pay the rent
                String msg =  Language.getInstance().getLanguageValue("landOnFieldPlayerBankrupt",name,owner.getIdentifier());
                gameState.getGuiController().displayMsg(msg);

                //Cant pay the rent
                while (currentPlayer.getBalance() < rentToPay) {
                    if (gameState.getFieldController().countHouse(gameState.getFieldController().ownsColourGroup(currentPlayer)) == 0) {
                        gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("disqualified"));
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
