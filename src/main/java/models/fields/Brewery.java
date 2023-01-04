package models.fields;

import models.Player;
import org.apache.commons.lang.NotImplementedException;

public class Brewery extends Property{

    private int rent0;
    private int rent1;


    public void setRent0(int rent0) {
        this.rent0 = rent0;
    }

    public void setRent1(int rent1) {
        this.rent1 = rent1;
    }

    public int getRent0() {
        return rent0;
    }

    public int getRent1() {
        return rent1;
    }

    public Effect fieldEffect(Player player){
        if (owner == null) {
            if (player.getBalance() > price) {
                return Effect.BUY;
            } else {
                return Effect.NONE;
            }
        } else {
            return Effect.RENT;
        }
    }
}
