package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Tax extends Field{

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState){
        throw new NotImplementedException();
    }
}
