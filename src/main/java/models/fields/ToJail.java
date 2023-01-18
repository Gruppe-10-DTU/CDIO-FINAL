package models.fields;

import models.dto.IGameStateDTO;
import models.Language;

public class ToJail extends Field {

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier) {
        gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("landOnGoToJail"));
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
        gameState.getGuiController().movePlayer(gameState.getActivePlayer(), gameState.isReverse());
    }
}
