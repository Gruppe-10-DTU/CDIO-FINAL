package models;

import java.awt.*;

public class Character {
    private String name;
    private String image;
    private String color;


    public Character(String name, String image, String color) {
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

    public String getColor() {
        return color;
    }
}
