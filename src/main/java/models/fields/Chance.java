package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Chance extends Field {
    private int number;

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState){

        gameState.getGuiController().displayMsg("IKKE IMPLEMENTERET!!!");
        return gameState;
    }
}
