package models.fields;

import models.Player;
import org.apache.commons.lang.NotImplementedException;

public class Empty extends Field {

    public Effect fieldEffect(Player player){
        throw new NotImplementedException();
    }

}
