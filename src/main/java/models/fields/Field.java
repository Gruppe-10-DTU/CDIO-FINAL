package models.fields;
import models.dto.GameStateDTO;

public abstract class Field {
    protected String name;
    protected int iD;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public int getID() {
        return iD;
    }

    public GameStateDTO fieldEffect(GameStateDTO gameState) {
        return fieldEffect(gameState, 1);
    }

    public abstract GameStateDTO fieldEffect(GameStateDTO gameState, int rentMultiplier);
}
