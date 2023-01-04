package models.fields;
import gui_fields.GUI_Field;
import models.Player;

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

    public abstract Effect fieldEffect(Player player);
}
