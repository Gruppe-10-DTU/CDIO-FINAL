package models.chanceCards;

public class Tax extends ChanceCard {

    private int houseTax;
    private int hotelTax;


    public Tax (String Name, String Description, int Value_1, int Value_2){
        super(Name, Description);
        this.houseTax = Value_1;
        this.hotelTax = Value_2;
    }

    public int getHouseTax() {
        return houseTax;
    }

    public int getHotelTax() {
        return hotelTax;
    }
}
