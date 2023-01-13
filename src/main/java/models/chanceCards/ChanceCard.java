package models.chanceCards;

import models.dto.IGameStateDTO;

public abstract class ChanceCard {
    protected final String NAME;
    protected final String description;

    /**
     * Super constructor
     * @param NAME        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChanceCard(String NAME, String Description){
        this.NAME = NAME;
        this.description = Description;
    }
    public abstract void chanceEffect(IGameStateDTO gameState);

    public String getName() {
        return NAME;
    }

}
