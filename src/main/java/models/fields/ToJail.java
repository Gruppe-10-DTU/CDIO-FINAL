package models.fields;

import models.Player;
import org.apache.commons.lang.NotImplementedException;

public class ToJail extends Field {

    private int spaceToJail;

    public ToJail(int spaceNumber){
        spaceToJail=spaceNumber;
    }

    public void fieldEffect(Player player){
        throw new NotImplementedException();
    }
}
