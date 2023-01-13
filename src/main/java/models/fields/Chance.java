package models.fields;

import models.chanceCards.ChanceCard;
import models.dto.IGameStateDTO;

public class Chance extends Field {

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier){
        gameState.getGuiController().displayMsg("Du er landet på Prøv Lykken, og må derfor trække et kort.");
        ChanceCard drawnCard = gameState.getChanceCardDeck().drawCard();
        drawnCard.chanceEffect(gameState);
    }
}
