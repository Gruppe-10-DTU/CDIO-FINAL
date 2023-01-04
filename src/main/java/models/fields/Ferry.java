package models.fields;

public class Ferry extends Field{
    private int price;
    private int rent0;
    private int rent1;
    private int rent2;
    private int rent3;

    public void setPrice(int price) {
        this.price = price;
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

    public int getPrice() {
        return price;
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
}
