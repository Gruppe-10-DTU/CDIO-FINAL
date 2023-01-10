package controllers;


import models.Language;
import models.Player;

public class FieldControllerStub extends FieldController {

    public FieldControllerStub(){

    }

    /**
     * Creates a Hashmap collecting the total value of all properties owned by a player
     *
     * @param player
     * @return Hashmap, key: player objects, value: the total property value of set player
     */
    @Override
    public int playerPropertyValues(Player player) {
        return 3000;
    }
}
