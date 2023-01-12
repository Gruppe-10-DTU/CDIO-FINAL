package models.fields;

import models.chanceCards.ChanceCard;
import models.dto.IGameStateDTO;

public class Chance extends Field {
    private int number;

    @Override
    public void fieldEffect(IGameStateDTO gameState){
        ChanceCard drawnCard = gameState.getChancecardDeck().drawCard();
        drawnCard.chanceEffect(gameState);
    }
}
