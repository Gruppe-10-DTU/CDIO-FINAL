package models.fields;

import models.dto.IGameStateDTO;

public class Refuge extends Field{

    @Override
    public void fieldEffect(IGameStateDTO gameState){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg("Du er landet på gratis parkering");
    }
}
