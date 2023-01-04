package models;

import java.awt.*;

public class Character {
    private String name;
    private String image;
    private int color;


    public Character(String name, String image, int color) {
        this.name = name;
        this.image = image;
        this.color = color;
    }

    public String getImage() {
        return image;
    }
    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
