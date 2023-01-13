package models.fields;

import models.dto.IGameStateDTO;

public class ToJail extends Field {

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier) {
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
    }
}
