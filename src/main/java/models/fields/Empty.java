package models.fields;

import models.dto.IGameStateDTOField;

public class Empty extends Field {

    @Override
    public void fieldEffect(IGameStateDTOField gameState){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg("Du er landet på gratis parkering. Slap af og nyd en læske");
    }

}
