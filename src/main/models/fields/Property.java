package game.models.fields;

import game.models.fields.Field;

public class Property extends Field {
    private String color;
    private int price;


    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}