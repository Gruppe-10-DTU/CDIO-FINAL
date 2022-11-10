package controllers;

import models.Player;
import models.fields.*;
import models.Language;

import java.util.ArrayList;

public class FieldController {

    ArrayList<Object> fieldArrayList = new ArrayList<>();

    public FieldController (ArrayList<ArrayList<String>> arrayList) {
        for (int i=0; i < arrayList.size(); i++) {
            String fieldType = arrayList.get(i).get(0);

            //Language language = new Language();

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
                    //start.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "Property":
                    Property property = new Property();
                    fieldArrayList.add(property);
                    property.setID(i);
                    property.setPrice(arrayList.get(i).get(2));
                    property.setColor(arrayList.get(i).get(1));
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
    }

    public void jailPlayer(Player player) {

    }

    public int playerProbertyValues(Player player) {
        int value = 0;
        return value;
    };

    public void moveToColor(String color) {

    }

    public void setOwner(Player player, int probertyId) {

    }

    /*public Object getField(int fieldID) {

    }*/

    public ArrayList<Object> getFieldList() {
        return fieldArrayList;
    }
}
