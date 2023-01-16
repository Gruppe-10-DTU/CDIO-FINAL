package models.chanceCards;

import models.Player;
import models.dto.IGameStateDTO;
public class Tax extends ChanceCard {

    private final int HOUSE_TAX;
    private final int HOTEL_TAX;


    public Tax (String Name, String Description, int Value_1, int Value_2){
        super(Name, Description);
        this.HOUSE_TAX = Value_1;
        this.HOTEL_TAX = Value_2;
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState){
        gameState.getGuiController().showChanceCard(description);
        Player player = gameState.getActivePlayer();
        int[] buildingsOwned = gameState.getFieldController().housesAndHotelsOwned(player);
        int houseTax = buildingsOwned[0] * HOUSE_TAX;
        int hotelTax = buildingsOwned[1] * HOTEL_TAX;
        int totalTax = (houseTax + hotelTax)* -1;

        if (player.getBalance() > totalTax){
            player.setBalance(totalTax);
            gameState.getGuiController().updatePlayer(player);
        } else {
            gameState.getGuiController().displayMsg("Du kan ikke betale din afgift");
            //Optional house selling
            gameState.getPlayerController().removePlayer(player.getID());
        }
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
