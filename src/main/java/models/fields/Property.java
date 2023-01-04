package models.fields;

import models.Player;

public abstract class Property extends Field{
    protected int price;
    protected Player owner;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Player getOwner() {return owner;}
    public void setOwner(Player owner) {this.owner = owner;}
}
