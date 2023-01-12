package models.fields;

import models.chanceCards.ChanceCard;
import models.dto.IGameStateDTOField;

public class Chance extends Field {
    private int number;

    @Override
    public void fieldEffect(IGameStateDTOField gameState){
        ChanceCard drawnCard = gameState.getChancecardDeck().drawCard();
        drawnCard.chanceEffect(gameState);
    }
}
