package models.fields;

import models.dto.GameStateDTO;

public class Empty extends Field {

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState, int rentMultiplier){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg("Du er landet på gratis parkering. Slap af og nyd en læske");
        return gameState;
    }

}
