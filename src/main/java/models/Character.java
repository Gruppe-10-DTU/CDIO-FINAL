package models;

import java.awt.*;

public class Character {
    private String name;
    private Color color;


    public Character(String name, int colorCode) {
        this.name = name;
        this.color = setColor(colorCode);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    private  Color setColor(int colorCode) {
        switch (colorCode) {
            case 0:
                return Color.YELLOW;
            case 1:
                return Color.blue;
            case 2:
                return Color.white;
            case 3:
                return Color.black;
            case 4:
                return Color.red;
            case 5:
                return Color.gray;
            default:
                return Color.PINK;
        }
    }
}
