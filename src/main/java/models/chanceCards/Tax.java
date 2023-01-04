package models.chanceCards;

public class Tax extends ChanceCard {

    private final int HOUSE_TAX;
    private final int HOTEL_TAX;


    public Tax (String Name, String Description, int Value_1, int Value_2){
        super(Name, Description);
        this.HOUSE_TAX = Value_1;
        this.HOTEL_TAX = Value_2;
    }

    public int getHOUSE_TAX() {
        return HOUSE_TAX;
    }

    public int getHOTEL_TAX() {
        return HOTEL_TAX;
    }
}
