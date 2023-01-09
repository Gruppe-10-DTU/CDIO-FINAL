package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Start extends Field{

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState){

        //Key fieldLandStart
        gameState.getGuiController().displayMsg("Du er landet på start. Tillykke med de gratis penge");

        return gameState;
    }
}
