package models.fields;

import models.Language;
import models.chanceCards.ChanceCard;
import models.dto.IGameStateDTO;

public class Chance extends Field {

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier){
        gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("landOnChance"));
        ChanceCard drawnCard = gameState.getChanceCardDeck().drawCard();
        drawnCard.chanceEffect(gameState);
    }
}
