package models.fields;

import models.dto.IGameStateDTO;

public class ToJail extends Field {

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier) {
        gameState.getGuiController().displayMsg("Du fængsles, og modtager ikke penge for at passere start.");
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
        gameState.getGuiController().movePlayer(gameState.getActivePlayer(), gameState.isReverse());
    }
}
