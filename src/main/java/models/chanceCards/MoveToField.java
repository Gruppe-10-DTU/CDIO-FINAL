package models.chanceCards;

import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class MoveToField extends ChanceCard{

    private final int FIELD_ID;
    private final boolean PASS_START_BONUS;

    /**
     * Constructor for the Chancecards that move the player to a specific field.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     * @param FIELD_ID   The name of the field to move to
     */
    public MoveToField(String Name, String Description, boolean PassStartBonus, int FIELD_ID) {
        super(Name, Description);
        this.PASS_START_BONUS = PassStartBonus;
        this.FIELD_ID = FIELD_ID;
    }

    @Override
    public GameStateDTO chanceEffect(GameStateDTO gameState){
        throw new NotImplementedException();
    }

    public int getFieldID() {
        return FIELD_ID;
    }

    public boolean getPASS_START_BONUS() {
        return PASS_START_BONUS;
    }
}
