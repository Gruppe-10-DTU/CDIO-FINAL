package models.fields;

import models.dto.GameStateDTO;

public class ToJail extends Field {

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState, int rentMultiplier) {
        gameState.getGuiController().displayMsg("Du fængsles, og modtager ikke penge for at passere start.");
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
        gameState.getGuiController().movePlayer(gameState.getActivePlayer());
        return gameState;
    }
}
