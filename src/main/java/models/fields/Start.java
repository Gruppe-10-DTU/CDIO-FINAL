package models.fields;

import models.dto.GameStateDTO;

public class Start extends Field{

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState, int rentMultiplier){

        //Key fieldLandStart
        gameState.getGuiController().displayMsg("Du er landet på start. Tillykke med de gratis penge");

        return gameState;
    }
}
