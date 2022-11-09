package game.models.fields;

import java.util.ArrayList;

public class FieldController {
    public ArrayList<Object> gameFields (String[] array) {

        ArrayList<Object> fieldArrayList = new ArrayList<>();
        //Language language = new Language("English");

        for (int i=0; i < array.length; i++) {
            String fieldType = array[i];

            switch (fieldType) {
                case "Empty":
                    Empty empty = new Empty();
                    fieldArrayList.add(empty);
                    empty.setID(i);
                    //empty.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "Start":
                    Start start = new Start();
                    fieldArrayList.add(start);
                    start.setID(i);
                    break;
                case "Property":
                    Property property = new Property();
                    fieldArrayList.add(property);
                    property.setID(i);
                    //property.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "Chance":
                    Chance chance = new Chance();
                    fieldArrayList.add(chance);
                    chance.setID(i);
                    //chance.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "ToJail":
                    ToJail toJail = new ToJail(-12);
                    fieldArrayList.add(toJail);
                    toJail.setID(i);
                    //toJail.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "Jail":
                    Jail jail = new Jail();
                    fieldArrayList.add(jail);
                    jail.setID(i);
                    //jail.setName(language.getLanguageValue("fieldName" + i));
                    break;
            }
        }

        return fieldArrayList;

    };
}
