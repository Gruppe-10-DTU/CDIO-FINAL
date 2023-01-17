package models.fields;

import models.dto.IGameStateDTO;

public class Empty extends Field {

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg(language.getLanguageValue("landOnFreeParking"));
    }

}
