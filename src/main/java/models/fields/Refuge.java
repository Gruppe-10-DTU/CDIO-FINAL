package models.fields;

import models.dto.IGameStateDTOField;

public class Refuge extends Field{

    @Override
    public void fieldEffect(IGameStateDTOField gameState){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg("Du er landet på gratis parkering");
    }
}
