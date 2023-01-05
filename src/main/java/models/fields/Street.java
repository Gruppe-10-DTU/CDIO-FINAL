package models.fields;
import models.Player;
import models.dto.GameStateDTO;


public class Street extends Property{

    private String color;
    //private int price;
    private int housePrice;

    private int houseAmount = 0;

    private int[] rent = new int[6];


    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState) {

        if (owner == null) {
            Player currentPlayer = gameState.getActivePlayer();
            if (currentPlayer.getBalance() > price) {
                String msg = "Du er landet på " + name + " Vil du købe den for " + price + "kr";
                boolean wantToBuy = gameState.getGuiController().getUserLeftButtonPressed(msg, "Ja", "Nej");

                if (wantToBuy) {
                    owner = currentPlayer;
                    currentPlayer.setBalance(currentPlayer.getBalance() - price);
                } else {
                    //Auktion
                }
            } else {
                //Player cant buy (possibly give the player an option to sell other values and then buy?)
                String msg = "Du er landet på " + name + " Til en værdi af " + price + "og har dessværre ikke råd til at købe den";

                gameState.getGuiController().displayMsg(msg);
            }
        } else {
            //Pay rent
            int rentToPay = rent[houseAmount];
            String msg = "Du er landet på " + name + "Der ejes af " + owner.getIdentifier() + " betal leje " + rentToPay;


        }
        return gameState;
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

    public void setRent(int index, int rentAmound) {
        this.rent[index] = rentAmound;
    }

    public int getHouseAmount() {
        return houseAmount;
    }
}
