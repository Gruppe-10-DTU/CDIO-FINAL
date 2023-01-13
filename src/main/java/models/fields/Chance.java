package models.fields;

import models.chanceCards.ChanceCard;
import models.dto.IGameStateDTO;

public class Chance extends Field {
    private int number;

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier){
        ChanceCard drawnCard = gameState.getChanceCardDeck().drawCard();
        drawnCard.chanceEffect(gameState);
    }
}
