package models.fields;
import models.dto.IGameStateDTO;

import java.util.Map;


public class Street extends Property {

    private String color;
    //private int price;
    private int housePrice;

    private int houseAmount = 0;

    private boolean hotel = false;


    private int[] rent = new int[6];

    @Override
    protected int getRentAmount(IGameStateDTO gameState) {
        Map<String, Street[]> ownsGroup = gameState.getFieldController().ownsColourGroup(owner);
        if(ownsGroup.containsKey(this.color) && houseAmount == 0){
            return rent[houseAmount]*2;
        }
        return rent[houseAmount];
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(String price) {
        this.price = Integer.parseInt(price);
    }


    public void setHouseAmount(int houseAmount) {
        this.houseAmount = houseAmount;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int[] getRent() {
        return rent;
    }

    public void setRent(int index, int rentAmount) {
        this.rent[index] = rentAmount;
    }

    public int getHouseAmount() {
        return houseAmount;
    }

    public boolean isHotel() {
        return hotel;
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

}
