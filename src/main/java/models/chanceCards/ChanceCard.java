package models.chanceCards;

import models.dto.GameStateDTO;

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

    public abstract GameStateDTO chanceEffect(GameStateDTO gameState);

    public String getType(){
        return this.getClass().toString().replaceAll("class models.chanceCards.", "");
    }

    public String getName() {
        return NAME;
    }

    public String getDescription() {
        return description;
    }
}
