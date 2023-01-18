package models.fields;

import models.Language;
import models.dto.IGameStateDTO;

public class Refuge extends Field{

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("landOnFreeParking"));
    }
}
