package models.fields;
import models.Player;
import models.dto.IGameStateDTO;

import java.util.Map;


public class Street extends Property {

    private String color;
    //private int price;
    private int housePrice;

    private int houseAmount = 0;

    private boolean hotel = false;


    private int[] rent = new int[6];


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
                String msg = "Du er landet på " + name + " Til en værdi af " + price + "og har desværre ikke råd til at købe den";
                gameState.getGuiController().displayMsg(msg);

                this.auction(gameState);
            }
        } else {

            Map<String, Street[]> ownsGroup = gameState.getFieldController().ownsColourGroup(owner);

            int rentToPay;

            if (ownsGroup.containsKey(color)) {

                if (houseAmount == 0) {
                    rentToPay = rent[houseAmount]*2;
                } else {
                    rentToPay = rent[houseAmount];
                }
            } else {
                rentToPay = rent[0];
            }


            if (owner == currentPlayer) {
                String msg = "Du er landet på din egen grund";
                gameState.getGuiController().displayMsg(msg);

            } else if (gameState.getFieldController().isJailed(owner)) {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " men da ejeren er i fængsel betales ingen leje ";
                gameState.getGuiController().displayMsg(msg);

            } else if (currentPlayer.setBalance(-rentToPay)) {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " betal leje " + rentToPay;
                gameState.getGuiController().displayMsg(msg);

                owner.setBalance(rentToPay);
                gameState.getGuiController().updatePlayer(owner);
            } else {
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



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(String price) {
        this.price = Integer.parseInt(price);
    }


    public void setHouseAmount(int houseAmount) {
        this.houseAmount = houseAmount;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int[] getRent() {
        return rent;
    }

    public void setRent(int index, int rentAmount) {
        this.rent[index] = rentAmount;
    }

    public int getHouseAmount() {
        return houseAmount;
    }

    public boolean isHotel() {
        return hotel;
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

}
