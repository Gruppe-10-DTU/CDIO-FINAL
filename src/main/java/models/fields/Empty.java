package models.fields;

import models.dto.IGameStateDTO;

public class Empty extends Field {

    @Override
    public void fieldEffect(IGameStateDTO gameState){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg("Du er landet på gratis parkering. Slap af og nyd en læske");
    }

}
