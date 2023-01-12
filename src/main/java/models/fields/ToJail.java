package models.fields;

import models.dto.IGameStateDTOField;

public class ToJail extends Field {

    @Override
    public void fieldEffect(IGameStateDTOField gameState) {
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
    }
}
