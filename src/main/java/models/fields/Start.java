package models.fields;

import models.dto.IGameStateDTO;

public class Start extends Field{

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier){

        //Key fieldLandStart
        gameState.getGuiController().displayMsg("Du er landet på start. Tillykke med de gratis penge");

    }
}
