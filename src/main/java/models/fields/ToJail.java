package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class ToJail extends Field {

    private int spaceToJail;

    public ToJail(int spaceNumber){
        spaceToJail=spaceNumber;
    }

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState){
        throw new NotImplementedException();
    }
}
