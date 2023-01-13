package models.fields;

import models.dto.GameStateDTO;

public class ToJail extends Field {

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState, int rentMultiplier) {
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
        return gameState;
    }
}
