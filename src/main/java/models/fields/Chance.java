package models.fields;

import models.chanceCards.ChanceCard;
import models.dto.GameStateDTO;

public class Chance extends Field {
    private int number;

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState, int rentMultiplier){
        gameState.getGuiController().displayMsg("Du er landet på Prøv Lykken, og må derfor trække et kort.");
        ChanceCard drawnCard = gameState.getChancecardDeck().drawCard();
        drawnCard.chanceEffect(gameState);
        return gameState;
    }
}
