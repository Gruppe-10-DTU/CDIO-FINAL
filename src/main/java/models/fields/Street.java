package models.fields;
import models.Player;
import models.dto.GameStateDTO;


public class Street extends Property{

    private String color;
    //private int price;
    private int houseRent;

    private int houseAmount;
    private int rent0;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int rent5;


    public GameStateDTO fieldEffect(GameStateDTO gameState) {
        GameStateDTO newGameState = gameState;

        if (owner == null) {
            if (gameState.getActivePlayer().getBalance() > price) {
                gameState.getGuiController().displayMsg("Du er landet på " + name + " Vil du købe den for " + price + "kr");

                //Bruger interaktion

                owner = gameState.getActivePlayer();
                newGameState.getActivePlayer().setBalance(gameState.getActivePlayer().getBalance() - price);

                return newGameState;
            } else {
                //Player cant buy
                return newGameState;
            }
        } else {
            //Pay rent
            return newGameState;
        }
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

    public int getHouseAmount() {
        return houseAmount;
    }
}
