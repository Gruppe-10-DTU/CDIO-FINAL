package models.chanceCards;

import models.Player;
import models.dto.IGameStateDTO;

public class Tax extends ChanceCard {

    private final int houseTax;
    private final int hotelTax;


    public Tax(String name, String description, int houseTax, int hotelTax) {
        super(name, description);
        this.houseTax = houseTax;
        this.hotelTax = hotelTax;
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState) {
        gameState.getGuiController().showChanceCard(description);
        Player player = gameState.getActivePlayer();
        int[] buildingsOwned = gameState.getFieldController().housesAndHotelsOwned(player);
        int houseTax = buildingsOwned[0] * this.houseTax;
        int hotelTax = buildingsOwned[1] * this.hotelTax;
        int totalTax = (houseTax + hotelTax) * -1;

        if (player.setBalance(totalTax) || gameState.getFieldController().sell(player,totalTax, gameState)) {
            gameState.getGuiController().updatePlayer(player);
        } else {
            gameState.getGuiController().displayMsg("Du kan ikke betale din afgift");
            //Optional house selling
            gameState.getPlayerController().removePlayer(player.getID());
        }
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
