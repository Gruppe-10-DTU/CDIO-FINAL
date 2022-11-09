package game.models.fields;

import java.util.ArrayList;

public class main {
    public static void main (String[] args) {

        String[] fieldTypeArray = {"Empty", "Property", "Property", "Chance", "Property", "Property", "Empty", "Property", "Property", "Chance", "Property", "Property", "Empty", "Property", "Property", "Chance", "Property", "Property", "ToJail", "Property", "Property", "Chance", "Property", "Property",};
        FieldController fieldController = new FieldController();

        ArrayList<Object> gameFields = fieldController.gameFields(fieldTypeArray);


    };

}
