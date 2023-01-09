package models.fields;

import models.chanceCards.ChanceCard;
import models.dto.GameStateDTO;

public class Chance extends Field {
    private int number;

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState){
        ChanceCard drawnCard = gameState.getChancecardDeck().drawCard();
        drawnCard.chanceEffect(gameState);
        return gameState;
    }
}
