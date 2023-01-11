package controllers;

import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import models.fields.*;

import java.lang.reflect.Array;
import java.util.*;

public class FieldController {

    protected ArrayList<Field> fieldArrayList = new ArrayList<>();

    private Language language;

    private static int housePool = StartValues.getInstance().getValue("housePool");

    private static int hotelPool = StartValues.getInstance().getValue("hotelPool");


    /**
     * The constructer recieves the language and constructs an arraylist of field objects in the corect language
     */
    public FieldController(Language language) {
        this.language = language;

        String path = "/GamePack/fields.csv";

        CSVReader csvReader = null;

        csvReader = new CSVReader(path, ",", true);
        ArrayList<ArrayList<String>> fieldData = csvReader.getDataAsArrList();

        createFieldArray(fieldData);
    }

    public FieldController() {
    }

    protected void createFieldArray(ArrayList<ArrayList<String>> fieldData) {

        for (int i = 0; i < fieldData.size(); i++) {
            String fieldType = fieldData.get(i).get(2);

            switch (fieldType) {
                case "start":
                    Start start = new Start();
                    fieldArrayList.add(start);
                    start.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    start.setName(fieldData.get(i).get(0));
                    //start.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "street":
                    Street street = new Street();
                    fieldArrayList.add(street);
                    street.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    street.setName(fieldData.get(i).get(0));
                    street.setPrice(fieldData.get(i).get(3));
                    street.setHousePrice(Integer.parseInt(fieldData.get(i).get(4)));
                    street.setRent(0, Integer.parseInt(fieldData.get(i).get(5)));
                    street.setRent(1, Integer.parseInt(fieldData.get(i).get(6)));
                    street.setRent(2, Integer.parseInt(fieldData.get(i).get(7)));
                    street.setRent(3, Integer.parseInt(fieldData.get(i).get(8)));
                    street.setRent(4, Integer.parseInt(fieldData.get(i).get(9)));
                    street.setRent(5, Integer.parseInt(fieldData.get(i).get(10)));
                    street.setColor(fieldData.get(i).get(11));
                    //property.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "chance":
                    Chance chance = new Chance();
                    fieldArrayList.add(chance);
                    chance.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    chance.setName(fieldData.get(i).get(0));
                    //chance.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "jail":
                    Jail jail = new Jail(Integer.parseInt(fieldData.get(i).get(3)));
                    fieldArrayList.add(jail);
                    jail.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    jail.setName(fieldData.get(i).get(0));
                    //jail.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "tojail":
                    ToJail toJail = new ToJail();
                    fieldArrayList.add(toJail);
                    toJail.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    toJail.setName(fieldData.get(i).get(0));
                    //toJail.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "brewery":
                    Brewery brewery = new Brewery();
                    fieldArrayList.add(brewery);
                    brewery.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    brewery.setName(fieldData.get(i).get(0));
                    brewery.setPrice(Integer.parseInt(fieldData.get(i).get(3)));
                    brewery.setRent0(Integer.parseInt(fieldData.get(i).get(5)));
                    brewery.setRent1(Integer.parseInt(fieldData.get(i).get(6)));
                    break;
                case "ferry":
                    Ferry ferry = new Ferry();
                    fieldArrayList.add(ferry);
                    ferry.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    ferry.setName(fieldData.get(i).get(0));
                    ferry.setPrice(Integer.parseInt(fieldData.get(i).get(3)));
                    ferry.setRent(0, Integer.parseInt(fieldData.get(i).get(5)));
                    ferry.setRent(1, Integer.parseInt(fieldData.get(i).get(6)));
                    ferry.setRent(2, Integer.parseInt(fieldData.get(i).get(7)));
                    ferry.setRent(3, Integer.parseInt(fieldData.get(i).get(8)));
                    break;
                case "refugee":
                    Refuge refuge = new Refuge();
                    fieldArrayList.add(refuge);
                    refuge.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    refuge.setName(fieldData.get(i).get(0));
                    break;
                case "tax":
                    Tax tax = new Tax(fieldData.get(i).get(0), Integer.parseInt(fieldData.get(i).get(1)), Integer.parseInt(fieldData.get(i).get(3)),Integer.parseInt(fieldData.get(i).get(4)));
                    fieldArrayList.add(tax);
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
                if (((Jail) field).getName().equals("I fængsel/På besøg")) {
                    ((Jail) field).setInJailAdd(player);
                    int jailLocation = ((Jail) field).getID();
                    player.setLocation(jailLocation);
                    break;
                }
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

    public GameStateDTO landOnField(GameStateDTO gamestate) {
        Field currentField = fieldArrayList.get(gamestate.getActivePlayer().getLocation());

        GameStateDTO newGameState = currentField.fieldEffect(gamestate);

        return newGameState;
    }

    /**
     * Creates a Hashmap collecting the total value of all properties owned by a player
     *
     * @return Hashmap, key: player objects, value: the total property value of set player
     */
    public int playerPropertyValues(Player player) {
        int totalAmount = 0;
        for (Field field : fieldArrayList) {
            if (field instanceof Property && ((Property) field).getOwner() == player) {
                totalAmount += ((Property) field).getPrice();
                if(field instanceof Street){
                    totalAmount += ((Street) field).getHouseAmount()*((Street) field).getHousePrice();
                }
            }
        }
        return totalAmount;
    }


    public void setOwner(Player player, int propertyId) {
        Field property = fieldArrayList.get(propertyId);
        if (property instanceof Street) {
            ((Street) property).setOwner(player);
        }
    }

    public Field getField(int fieldID) {
        return fieldArrayList.get(fieldID);
    }

    public ArrayList<Field> getFieldList() {
        return fieldArrayList;
    }



    /**
     * see if a propertys neighbor have the same owner
     * @param property the property in question
     * @return true if the same owner, otherwise false
     */
    public boolean sameOwner(Street property) {
        Street property2;
        if (property.getID() % 3 == 1) {
            //If the property is the first one, %3 will always be one and we'll add one to get the neighbor and compare the owners
            property2 = (Street) fieldArrayList.get(property.getID() + 1);
        } else {
            //If the property is the second one, %3 will always be 2 and we'll subtract one to get the neighbor and compare the owners
            property2 = (Street) fieldArrayList.get(property.getID() - 1);
        }
        return property2.getOwner() != null && property.getOwner().equals(property2.getOwner());
    }

    public boolean sellField(Street property, Player buyer) {
        return true;
    }

    public Street[] getFieldOtherPlayers(Player player) {
        return fieldArrayList.stream().filter(field -> field instanceof Street && ((Street) field).getOwner() != player).toArray(Street[]::new);
    }

    public Street[] getFieldsOfPlayer(Player player) {
        return fieldArrayList.stream().filter(field -> field instanceof Street && ((Street) field).getOwner() == player).toArray(Street[]::new);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldArrayList.size(); i++) {
            sb.append("Field" + i + ": " + fieldArrayList.get(i).getName() + ", ");
        }
        String str = sb.toString();
        return str;
    }

    public boolean isJailed(Player player) {
        return ((Jail) fieldArrayList.get(10)).isInJail(player);
    }
/*
    public int propertyCount(Street property){
        int amountOfColour = 0;
        for (int i = 0; i < fieldArrayList.size(); i++) {
            if (fieldArrayList.get(i) instanceof Street) {
                if (((Street) fieldArrayList.get(i)).getColor().equals(property.getColor())) {
                    amountOfColour++;
                }
            }
        }
        return amountOfColour;
    }

 */

    public Street[] ownsColourGroup(Player player) {
        ArrayList<Street> localData = new ArrayList<>();
        ArrayList<Street> propertiesToUpgrade = new ArrayList<>();
        for (int i = 0; i < fieldArrayList.size(); i += 4) {
            for (int k = i; k <= i + 4; k++) {
                if (fieldArrayList.get(k) instanceof Street && ((Street) fieldArrayList.get(k)).getOwner() != player) {
                    localData.clear();
                    break;
                } else{
                    }if(fieldArrayList.get(k) instanceof Street && ((Street) fieldArrayList.get(k)).getHouseAmount() < 4 && ((Street) fieldArrayList.get(k)).getOwner() == player){
                        localData.add((Street) fieldArrayList.get(k));
                    }else if(fieldArrayList.get(k) instanceof Street && ((Street) fieldArrayList.get(k)).getHouseAmount() == 4 && ((Street) fieldArrayList.get(k)).getOwner() == player){
                    localData.remove((Street) fieldArrayList.get(k));
                }
            }
            if(!localData.isEmpty()){
                map.put(localData.get(0).getColor(), localData.toArray(Street[]::new));
                localData.clear();
                propertiesToUpgrade.addAll(localData);
            }

        }
        return propertiesToUpgrade.toArray(Street[]::new);
    }

    public Map<String,Street[]> buildEqual(Map<String, Street[]> sort){
        int minVal = 5;
        ArrayList<Street> sortedStreet = new ArrayList<>();
        for (Map.Entry<String,Street[]> entry: sort.entrySet()){
            for (int i = 0; i < entry.getValue().length; i++) {
                if(entry.getValue()[i].getHouseAmount() < minVal){
                    minVal = entry.getValue()[i].getHouseAmount();
                }
            }
            for (int i = 0; i < entry.getValue().length; i++) {
                if(entry.getValue()[i].getHouseAmount() == minVal && entry.getValue()[i].getHouseAmount() != 4 ){
                    sortedStreet.add(entry.getValue()[i]);
                }
            }
            if(!sortedStreet.isEmpty()){
                sort.replace(entry.getKey(), sortedStreet.toArray(Street[]::new));
                sortedStreet.clear();
            }


        }
        return sort;
    }


    public void addHouse(Street property) {
        property.setHouseAmount(property.getHouseAmount()+1);
        property.getOwner().setBalance(-property.getHousePrice());

    }
    public void addHotel(Street property){
        property.setHotel(true);
        property.getOwner().setBalance(-property.getHousePrice()*4);
    }
/*
    public Street[] getStreetsFromColor(String color) {
        ArrayList<Street> streets = new ArrayList<>();
        for (int i = 0; i < fieldArrayList.size(); i++) {
            if (fieldArrayList.get(i) instanceof Street) {
                if (((Street) fieldArrayList.get(i)).getColor().equals(color)) {
                    streets.add((Street) fieldArrayList.get(i));
                }
            }
        }
        Street[] sortedStreets = new Street[streets.size()];
        for (int i = 0; i < streets.size(); i++) {
            sortedStreets[i] = streets.get(i);
        }
        return sortedStreets;
    }

 */
/*
    public String streetsToString(Street[] streets){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < streets.length ; i++) {
            sb.append(streets[i].toString() + ",");
        }
        return sb.toString();
    }

 */

    public Street getStreetFromString(String street){
        Street field = null;
        for (int i = 0; i < fieldArrayList.size(); i++) {
            if(fieldArrayList.get(i) instanceof  Street && fieldArrayList.get(i).getName() == street){
                field = (Street) fieldArrayList.get(i);
            }
        }
        return field;
    }

}


