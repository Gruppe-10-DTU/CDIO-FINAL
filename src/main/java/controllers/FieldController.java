package controllers;

import models.Language;
import models.Player;
import models.fields.*;

import java.util.ArrayList;
import java.util.HashMap;

public class FieldController {

    protected ArrayList<Field> fieldArrayList = new ArrayList<>();

    private Language language;

    /**
     * The constructer recieves the language and constructs an arraylist of field objects in the corect language
     */
    public FieldController(Language language) {
        this.language = language;

        String path = "/GamePack/Fields.csv";

        CSVReader csvReader = null;

        csvReader = new CSVReader(path, ",", true);
        ArrayList<ArrayList<String>> fieldData = csvReader.getDataAsArrList();

        createFieldArray(fieldData);
    }

    protected void createFieldArray(ArrayList<ArrayList<String>> fieldData) {

        for (int i = 0; i < fieldData.size(); i++) {
            String fieldType = fieldData.get(i).get(0);

            switch (fieldType) {
                case "empty":
                    Empty empty = new Empty();
                    fieldArrayList.add(empty);
                    empty.setID(i);
                    //empty.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "start":
                    Start start = new Start();
                    fieldArrayList.add(start);
                    start.setID(i);
                    //start.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "street":
                    Street street = new Street();
                    fieldArrayList.add(street);
                    street.setID(i);
                    //property.setPrice(fieldData.get(i).get(2));
                    //property.setColor(fieldData.get(i).get(1));
                    //property.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "chance":
                    Chance chance = new Chance();
                    fieldArrayList.add(chance);
                    chance.setID(i);
                    //chance.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "toJail":
                    ToJail toJail = new ToJail(-12);
                    fieldArrayList.add(toJail);
                    toJail.setID(i);
                    //toJail.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "jail":
                    Jail jail = new Jail();
                    fieldArrayList.add(jail);
                    jail.setID(i);
                    //jail.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "brewery":
                    Brewery brewery = new Brewery();
                    fieldArrayList.add(brewery);
                    brewery.setID(i);
                    break;
                case "ferry":
                    Ferry ferry = new Ferry();
                    fieldArrayList.add(ferry);
                    ferry.setID(i);
                    break;
                case "refuge":
                    Refuge refuge = new Refuge();
                    fieldArrayList.add(refuge);
                    refuge.setID(i);
                    break;
                case "tax":
                    Tax tax = new Tax();
                    fieldArrayList.add(tax);
                    tax.setID(i);
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
            if (field instanceof Start.Property) {
                Player owner = ((Start.Property) field).getOwner();
                if (owner != null) {
                    if (!playerValue.containsKey(owner)) {
                        playerValue.put(owner, 0);
                    }
                    int propertyValue = ((Start.Property) field).getPrice();
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

            if (fieldArrayList.get(i) instanceof Start.Property) {
                String fieldColor = ((Start.Property) fieldArrayList.get(i)).getColor();

                if (fieldColor.equals(color)) {
                    newLocation = ((Start.Property) fieldArrayList.get(i)).getID();
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
        if (property instanceof Start.Property) {
            ((Start.Property) property).setOwner(player);
        }
    }
    public Start.Property[] getFreeFields(){
        return fieldArrayList.stream().filter(field -> field instanceof Start.Property && ((Start.Property) field).getOwner() == null).toArray(Start.Property[]::new);
    }
    public Field getField(int fieldID) {
        return fieldArrayList.get(fieldID);
    }

    public ArrayList<Field> getFieldList() {
        return fieldArrayList;
    }

    public boolean getFreeField(Player Player, int PropertyID) {
        Start.Property property = (Start.Property) fieldArrayList.get(PropertyID);
        return property.getOwner() == null;
    }

    /**
     * see if a propertys neighbor have the same owner
     * @param property the property in question
     * @return true if the same owner, otherwise false
     */
    public boolean sameOwner(Start.Property property){
        Start.Property property2;
        if(property.getID() % 3 == 1){
            //If the property is the first one, %3 will always be one and we'll add one to get the neighbor and compare the owners
            property2 = (Start.Property) fieldArrayList.get(property.getID()+1);
        }else{
            //If the property is the second one, %3 will always be 2 and we'll subtract one to get the neighbor and compare the owners
            property2 = (Start.Property) fieldArrayList.get(property.getID()-1);
        }
        return property2.getOwner() != null && property.getOwner().equals(property2.getOwner());
    }
    public boolean sellField(Start.Property property, Player buyer){
        return true;
    }

    public Start.Property[] getFieldOtherPlayers(Player player) {
        return fieldArrayList.stream().filter(field -> field instanceof Start.Property && ((Start.Property) field).getOwner() != player).toArray(Start.Property[]::new);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < fieldArrayList.size(); i++) {
            sb.append("Field" + i + ": " + fieldArrayList.get(i).getName() + ", ");
        }
        String str = sb.toString();
        return str;
    }

}
