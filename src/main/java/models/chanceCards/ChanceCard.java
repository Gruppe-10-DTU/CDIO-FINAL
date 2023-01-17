package models.chanceCards;

import models.dto.IGameStateDTO;

public abstract class ChanceCard {
    protected final String name;
    protected final String description;

    /**
     * Super constructor
     * @param name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChanceCard(String name, String description){
        this.name = name;
        this.description = description;
    }
    public abstract void chanceEffect(IGameStateDTO gameState);

    public String getName() {
        return name;
    }

}
