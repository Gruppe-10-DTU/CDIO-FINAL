package models;

import java.awt.*;

public class Character {
    private String name;
    private String image;
    private Color color;


    public Character(String name, String image, int colorCode) {
        this.name = name;
        this.image = image;
        this.color = setColor(colorCode);
    }

    public String getImage() {
        return image;
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
                return Color.ORANGE;
            case 4:
                return Color.red;
            case 5:
                return Color.gray;
            default:
                return Color.PINK;
        }
    }
}
