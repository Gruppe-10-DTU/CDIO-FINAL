package models.fields;
import models.dto.IGameStateDTO;
import models.Language;

public abstract class Field {
    protected Language language;
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

    public void fieldEffect(IGameStateDTO gameState) {
        fieldEffect(gameState, 1);
    }

    public abstract void fieldEffect(IGameStateDTO gameState, int rentMultiplier);
}
