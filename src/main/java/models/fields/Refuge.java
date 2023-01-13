package models.fields;

import models.dto.GameStateDTO;

public class Refuge extends Field{

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState, int rentMultiplier){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg("Du er landet på gratis parkering");
        return gameState;
    }
}
