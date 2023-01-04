package models.fields;
import models.Player;


public class Street extends Field{

    private String color;
    private int price;
    private int houseREnt;
    private int rent0;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int rent5;
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
