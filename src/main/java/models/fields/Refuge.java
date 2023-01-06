package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Refuge extends Field{

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState){
        //Language key: fieldFreeParking
        gameState.getGuiController().displayMsg("Du er landet på gratis parkering");
        return gameState;
    }
}
