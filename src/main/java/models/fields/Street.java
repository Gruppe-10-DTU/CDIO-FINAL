package models.fields;
import models.Player;


public class Street extends Field{

    private String color;
    private int price;
    private int houseRent;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHouseRent(int houseRent) {
        this.houseRent = houseRent;
    }

    public void setRent0(int rent0) {
        this.rent0 = rent0;
    }

    public void setRent1(int rent1) {
        this.rent1 = rent1;
    }

    public void setRent2(int rent2) {
        this.rent2 = rent2;
    }

    public void setRent3(int rent3) {
        this.rent3 = rent3;
    }

    public void setRent4(int rent4) {
        this.rent4 = rent4;
    }

    public void setRent5(int rent5) {
        this.rent5 = rent5;
    }

    public int getHouseRent() {
        return houseRent;
    }

    public int getRent0() {
        return rent0;
    }

    public int getRent1() {
        return rent1;
    }

    public int getRent2() {
        return rent2;
    }

    public int getRent3() {
        return rent3;
    }

    public int getRent4() {
        return rent4;
    }

    public int getRent5() {
        return rent5;
    }
}
