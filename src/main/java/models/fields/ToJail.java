package models.fields;

import models.Player;
public class ToJail extends Field {

    private int spaceToJail;

    public ToJail(int spaceNumber){
        spaceToJail=spaceNumber;
    }


    public void jailPlayer (Player player) {

        player.setLocation(player.getLocation(), spaceToJail);
    }
}
