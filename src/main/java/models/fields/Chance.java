package models.fields;

import models.Player;
import org.apache.commons.lang.NotImplementedException;

public class Chance extends Field {
    private int number;

    public Effect fieldEffect(Player player){
        throw new NotImplementedException();
    }
}
