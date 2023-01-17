package models.fields;

import controllers.StartValues;
import models.Language;
import models.dto.IGameStateDTO;

public class Start extends Field{

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier){

        //Key fieldLandStart
        gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("landOnStart", "" + StartValues.getInstance().getValue("startingMoney")));

    }
}
