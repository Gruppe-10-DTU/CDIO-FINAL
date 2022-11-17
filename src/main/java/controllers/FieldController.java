package controllers;

import models.Language;
import models.Player;
import models.fields.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class FieldController {

    ArrayList<Field> fieldArrayList = new ArrayList<>();

    Language language;

    /**
     * The constructer recieves the language and constructs an arraylist of field objects in the corect language
     */
    public FieldController(Language language) {
        this.language = language;

        String path = System.getProperty("user.dir") + "/src/main/java/models/Fields.csv";

        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(path, ",", true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<ArrayList<String>> fieldData = csvReader.getDataAsArrList();

        createFieldArray(fieldData);
    }

    protected void createFieldArray(ArrayList<ArrayList<String>> fieldData) {

        for (int i = 0; i < fieldData.size(); i++) {
            String fieldType = fieldData.get(i).get(0);

            switch (fieldType) {
                case "Empty":
                    Empty empty = new Empty();
                    fieldArrayList.add(empty);
                    empty.setID(i);
                    empty.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "Start":
                    Start start = new Start();
                    fieldArrayList.add(start);
                    start.setID(i);
                    start.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "Property":
                    Property property = new Property();
                    fieldArrayList.add(property);
                    property.setID(i);
                    property.setPrice(fieldData.get(i).get(2));
                    property.setColor(fieldData.get(i).get(1));
                    property.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "Chance":
                    Chance chance = new Chance();
                    fieldArrayList.add(chance);
                    chance.setID(i);
                    chance.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "ToJail":
                    ToJail toJail = new ToJail(-12);
                    fieldArrayList.add(toJail);
                    toJail.setID(i);
                    toJail.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "Jail":
                    Jail jail = new Jail();
                    fieldArrayList.add(jail);
                    jail.setID(i);
                    jail.setName(language.getLanguageValue("fieldName" + i));
                    break;
            }
        }
    }

    /**
     * Recieves a player, locates the jail field, moves the player and jails them
     */
    public void jailPlayer(Player player) {

        for (Object field : fieldArrayList) {
            if (field instanceof Jail) {
                ((Jail) field).setInJailAdd(player);
                int jailLocation = ((Jail) field).getID();
                player.setLocation(jailLocation);
                break;
            }
        }
    }

    public void freePlayer(Player player) {
        for (Object field : fieldArrayList) {
            if (field instanceof Jail) {
                ((Jail) field).setInJailRemove(player);
                break;
            }
        }
    }

    /**
     * Creates a Hashmap collecting the total value of all properties owned by a player
     *
     * @return Hashmap, key: player objects, value: the total property value of set player
     */
    public HashMap<Player, Integer> playerPropertyValues() {

        HashMap<Player, Integer> playerValue = new HashMap<Player, Integer>();

        for (Object field : fieldArrayList) {
            if (field instanceof Property) {
                Player owner = ((Property) field).getOwner();
                if (owner != null) {
                    if (!playerValue.containsKey(owner)) {
                        playerValue.put(owner, 0);
                    }
                    int propertyValue = ((Property) field).getPrice();
                    int currentSum = playerValue.get(owner);

                    playerValue.put(owner, currentSum + propertyValue);
                }
            }
        }
        return playerValue;
    }

    /**
     * Recieves a player and a color and moves the player to the nearest instance of that color
     *
     * @param color  color of the field
     * @param player the plyaer that's moved
     * @return int location of the field
     */
    public int moveToColor(String color, Player player) {
        int location = player.getLocation();
        int newLocation = 0;
        int spaces = 0;

        int i = location;
        boolean foundColor = false;
        while (!foundColor) {
            i++;

            if (i >= fieldArrayList.size()) {
                i = 0;
            }

            if (fieldArrayList.get(i) instanceof Property) {
                String fieldColor = ((Property) fieldArrayList.get(i)).getColor();

                if (fieldColor.equals(color)) {
                    newLocation = ((Property) fieldArrayList.get(i)).getID();
                    foundColor = true;
                }
            }


            if (newLocation < location) {
                spaces = newLocation + (fieldArrayList.size() - location);
            } else {
                spaces = newLocation - location;
            }
        }
        return spaces;
    }

    public void setOwner(Player player, int propertyId) {
        Field property = fieldArrayList.get(propertyId);
        if (property instanceof Property) {
            ((Property) property).setOwner(player);
        }
    }
    public Property[] getFreeFields(){
        return fieldArrayList.stream().filter(field -> field instanceof Property && ((Property) field).getOwner() == null).toArray(Property[]::new);
    }
    public Field getField(int fieldID) {
        return fieldArrayList.get(fieldID);
    }

    public ArrayList<Field> getFieldList() {
        return fieldArrayList;
    }

    public boolean getFreeField(Player Player, int PropertyID) {
        Property property = (Property) fieldArrayList.get(PropertyID);
        return property.getOwner() == null;
    }
}
