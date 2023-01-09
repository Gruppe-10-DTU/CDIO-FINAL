package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Empty extends Field {

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg("Du er landet på gratis parkering. Slap af og nyd en læske");
        return gameState;
    }

}
