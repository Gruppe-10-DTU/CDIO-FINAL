package models.fields;


import models.Player;

public class Property extends Field {
    private String color;
    private int price;
    private Player owner;

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {return owner;}

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(String price) {
        this.price = Integer.parseInt(price);
    }

    public void setOwner(Player owner) {this.owner = owner;}
}