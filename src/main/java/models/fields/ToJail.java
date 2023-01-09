package models.fields;

import models.dto.GameStateDTO;

public class ToJail extends Property {

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState) {
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
        return gameState;
    }
}
