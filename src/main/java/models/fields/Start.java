package models.fields;

import models.dto.IGameStateDTOField;

public class Start extends Field{

    @Override
    public void fieldEffect(IGameStateDTOField gameState){

        //Key fieldLandStart
        gameState.getGuiController().displayMsg("Du er landet på start. Tillykke med de gratis penge");

    }
}
