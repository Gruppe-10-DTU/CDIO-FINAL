package models.chanceCards;

import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class GetOutOfJail extends ChanceCard{

    /**
     * Constructor for the get out of jail free card
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public GetOutOfJail(String Name, String Description) {
        super(Name, Description);
    }

    @Override
    public GameStateDTO chanceEffect(GameStateDTO gameState){
        throw new NotImplementedException();
    }
}
