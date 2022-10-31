package game.models;
import gui_fields.GUI_Field;

public class Field {
    private int effect;
    private String name;
    private String description;
    public Field(int effect,String name, String description){
        this.effect=effect;
        this.name = name;
        this.description = description;
    }
    public int getEffect() {
        return effect;
    }
    public String getName(){return name;}

    public String getDescription() {
        return description;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
