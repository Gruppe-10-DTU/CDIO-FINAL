package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import models.dto.IGameStateDTO;
import org.apache.commons.lang.ArrayUtils;

import java.util.Map;

public abstract class Property extends Field{
    protected int price;
    protected Player owner;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Player getOwner() {return owner;}
    public void setOwner(Player owner) {this.owner = owner;}

    protected abstract int getRentAmount(IGameStateDTO gameState);

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

            int rentToPay = getRentAmount(gameState)*rentMultiplier;

            if (owner == currentPlayer) {
                String msg = "Du er landet på din egen grund";
                gameState.getGuiController().displayMsg(msg);

            } else if (gameState.getFieldController().isJailed(owner)) {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " men da ejeren er i fængsel betales ingen leje ";
                gameState.getGuiController().displayMsg(msg);

            } else if (currentPlayer.setBalance(-rentToPay) || gameState.getFieldController().sell(currentPlayer, -rentToPay, gameState)) {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " betal leje " + rentToPay;
                gameState.getGuiController().displayMsg(msg);

                owner.setBalance(rentToPay);
                gameState.getGuiController().updatePlayer(owner);
            } else {
                String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " du har ikke råd til at betale lejen";
                gameState.getGuiController().displayMsg(msg);
                //Cant pay the rent

                while (!currentPlayer.setBalance(-rentToPay) ) {
                    Map<String, Street[]> buildingsToSell = gameState.getFieldController().sellEqual(ownsGroup);
                    if (buildingsToSell.size() == 0) {
                        gameState.getGuiController().displayMsg("You cannot pay the rent, and therefore you are disqualified from the game.");
                        gameState.getPlayerController().removePlayer(currentPlayer.getID());
                        return;
                    } else {
                        //Find the properties the player can sell for
                        String colorChosen = gameState.getGuiController().selectColorBuild("Choose where you want to sell buildings from", buildingsToSell.keySet().toArray(String[]::new));
                        String whereToSell = gameState.getGuiController().selectBuild("Sell building. 1 house sells for: " + buildingsToSell.get(colorChosen)[0].getHousePrice() / 2 + "", buildingsToSell.get(colorChosen));
                        Street target = gameState.getFieldController().getStreetFromString(whereToSell);
                        if (target.isHotel()) {
                            gameState.getFieldController().sellBuilding(gameState.getFieldController().getStreetFromString(whereToSell), 0);
                            gameState.getGuiController().guiRemoveHotel(gameState.getFieldController().getStreetFromString(whereToSell));
                        } else {
                            gameState.getGuiController().guiAddHouse(target, -1);
                        }
                        currentPlayer.setBalance(target.getHousePrice()/2);
                    }
                    gameState.getGuiController().updatePlayer(currentPlayer);
                }
            }
        }
    }

    protected void auction(IGameStateDTO gameStateDTO){
        Player[] players = gameStateDTO.getPlayerController().getPlayers();
        //Get the player after the current player
        int index = (ArrayUtils.indexOf(players,gameStateDTO.getActivePlayer())+ 1) % players.length;
        //Previous player. One player have to buy the property
        int k= -1;
        int bid = 0;
        do{
            if(players[index].getBalance()>bid && gameStateDTO.getGuiController().getUserLeftButtonPressed(players[index].getIdentifier() + " do you want to bid?", "yes", "no")){
                bid = gameStateDTO.getGuiController().getBid("Hvor meget vil du byde? Sidste bud var " + bid, bid + 1, players[index].getBalance());
                k=index;
            }
            index = (index + 1) % players.length;
        }while(k!=index);

        players[index].setBalance(-bid);
        this.owner = players[index];
    }
}
