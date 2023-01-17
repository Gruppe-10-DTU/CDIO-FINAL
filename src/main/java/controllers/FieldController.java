package controllers;

import models.Language;
import models.Player;
import models.dto.IGameStateDTO;
import models.fields.*;
import org.apache.commons.codec.language.bm.Lang;

import java.util.*;

public class FieldController {

    protected ArrayList<Field> fieldArrayList = new ArrayList<>();

    private int housePool = StartValues.getInstance().getValue("housePool");

    private int hotelPool = StartValues.getInstance().getValue("hotelPool");


    /**
     * The constructor receives the language and constructs an arraylist of field objects in the correct language
     */
    public FieldController() {
        String path = "/GamePack/fields.csv";

        CSVReader csvReader = null;

        csvReader = new CSVReader(path, ",", true);
        ArrayList<ArrayList<String>> fieldData = csvReader.getDataAsArrList();

        createFieldArray(fieldData);
    }

    protected void createFieldArray(ArrayList<ArrayList<String>> fieldData) {
        Language language = Language.getInstance();
        for (int i = 0; i < fieldData.size(); i++) {
            String fieldType = fieldData.get(i).get(2);

            switch (fieldType) {
                case "start":
                    Start start = new Start();
                    fieldArrayList.add(start);
                    start.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    start.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "street":
                    Street street = new Street();
                    fieldArrayList.add(street);
                    street.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    street.setName(language.getLanguageValue("fieldName" + i));
                    street.setPrice(fieldData.get(i).get(3));
                    street.setHousePrice(Integer.parseInt(fieldData.get(i).get(4)));
                    street.setRent(0, Integer.parseInt(fieldData.get(i).get(5)));
                    street.setRent(1, Integer.parseInt(fieldData.get(i).get(6)));
                    street.setRent(2, Integer.parseInt(fieldData.get(i).get(7)));
                    street.setRent(3, Integer.parseInt(fieldData.get(i).get(8)));
                    street.setRent(4, Integer.parseInt(fieldData.get(i).get(9)));
                    street.setRent(5, Integer.parseInt(fieldData.get(i).get(10)));
                    street.setColor(fieldData.get(i).get(11));
                    break;
                case "chance":
                    Chance chance = new Chance();
                    fieldArrayList.add(chance);
                    chance.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    chance.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "jail":
                    Jail jail = new Jail(Integer.parseInt(fieldData.get(i).get(3)));
                    fieldArrayList.add(jail);
                    jail.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    jail.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "tojail":
                    ToJail toJail = new ToJail();
                    fieldArrayList.add(toJail);
                    toJail.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    toJail.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "brewery":
                    Brewery brewery = new Brewery();
                    fieldArrayList.add(brewery);
                    brewery.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    brewery.setName(language.getLanguageValue("fieldName" + i));
                    brewery.setPrice(Integer.parseInt(fieldData.get(i).get(3)));
                    brewery.setRent0(Integer.parseInt(fieldData.get(i).get(5)));
                    brewery.setRent1(Integer.parseInt(fieldData.get(i).get(6)));
                    break;
                case "ferry":
                    Ferry ferry = new Ferry();
                    fieldArrayList.add(ferry);
                    ferry.setID(Integer.parseInt(fieldData.get(i).get(1)));
                    ferry.setName(language.getLanguageValue("fieldName" + i));
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
                    refuge.setName(language.getLanguageValue("fieldName" + i));
                    break;
                case "tax":
                    Tax tax = new Tax(language.getLanguageValue("fieldName" + i), Integer.parseInt(fieldData.get(i).get(1)), Integer.parseInt(fieldData.get(i).get(3)), Integer.parseInt(fieldData.get(i).get(4)));
                    fieldArrayList.add(tax);
                    break;
            }
        }
    }

    /**
     * Receives a player, locates the jail field, moves the player and jails them
     */
    public void jailPlayer(Player player) {
        /*
        Faster way but doesn't work with test
        ((Jail) fieldArrayList.get(10)).setInJailAdd(player);
        player.setLocation(10);

         */
        for (Object field : fieldArrayList) {
            if (field instanceof Jail) {
                ((Jail) field).setInJailAdd(player);
                int jailLocation = ((Jail) field).getID();
                player.setLocation(jailLocation);
                break;
            }
        }
    }

    public void landOnField(IGameStateDTO gameState) {
        landOnField(gameState, 1);
    }

    public void landOnField(IGameStateDTO gameState, int rentMultiplier) {
        Field currentField = fieldArrayList.get(gameState.getActivePlayer().getLocation());

        currentField.fieldEffect(gameState, rentMultiplier);
    }

    public int playerPropertyValues(Player player) {
        int totalAmount = 0;
        for (Field field : fieldArrayList) {
            if (field instanceof Property && ((Property) field).getOwner() == player) {
                totalAmount += ((Property) field).getPrice();
                if (field instanceof Street) {
                    totalAmount += ((Street) field).getHouseAmount() * ((Street) field).getHousePrice();
                }
            }
        }
        return totalAmount;
    }

    public Field getField(int fieldID) {
        return fieldArrayList.get(fieldID);
    }

    public ArrayList<Field> getFieldList() {
        return fieldArrayList;
    }

    public int distToFirstFerry(Player player, boolean gameInReverse) {
        int boardSize = StartValues.getInstance().getValue("boardSize");
        int direction = 1;
        if (gameInReverse) direction *= -1;
        int steps = player.getLocation();
        while (!(fieldArrayList.get(steps) instanceof Ferry)) {
            steps += direction;
            if (steps == boardSize) {
                steps = 0;
            } else if (steps < 0) {
                steps = boardSize - 1;
            }
        }
        if (gameInReverse && steps > player.getLocation()) {
            return (steps - (boardSize + player.getLocation()));
        } else if (!gameInReverse && steps < player.getLocation()) {
            return (steps + boardSize) - player.getLocation();
        } else {
            return (steps - player.getLocation());
        }
    }

    public int ferrysOwned(Player owner, int startField, int ferrys) {

        int ferrysOwned = 1;
        int lastFerry = startField;

        int i = 1;
        while (i < ferrys) {

            int currentFerry = lastFerry + 10;

            if (currentFerry >= 40) {
                currentFerry = currentFerry - 40;
            }
            Ferry nextFerry = (Ferry) getField(currentFerry);

            if (nextFerry.getOwner() == owner) {
                ferrysOwned++;
            }

            lastFerry = currentFerry;
            i++;
        }

        return ferrysOwned;
    }

    public int breweriesOwned(Player owner, int fieldId) {
        int breweriesOwned = 1;

        if (fieldId == 12) {
            Brewery Brewery28 = (Brewery) getField(28);
            if (Brewery28.getOwner() == owner) {
                breweriesOwned++;
            }
        } else {
            Brewery Brewery12 = (Brewery) getField(12);
            if (Brewery12.getOwner() == owner) {
                breweriesOwned++;
            }
        }
        return breweriesOwned;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldArrayList.size(); i++) {
            sb.append("Field").append(i).append(": ").append(fieldArrayList.get(i).getName()).append(", ");
        }
        return sb.toString();
    }

    public boolean isJailed(Player player) {
        return ((Jail) fieldArrayList.get(10)).isInJail(player);
    }

    public Map<String, Street[]> ownsColourGroup(Player player) {
        ArrayList<Street> localData = new ArrayList<>();
        HashMap<String, Street[]> map = new HashMap<>();
        ArrayList<Street> propertiesToUpgrade = new ArrayList<>();

        for (int i = 0; i < fieldArrayList.size(); i += 5) {
            for (int k = i; k <= i + 4; k++) {
                if (fieldArrayList.get(k) instanceof Street && ((Street) fieldArrayList.get(k)).getOwner() != player) {
                    localData.clear();
                    break;
                } else {
                    if (fieldArrayList.get(k) instanceof Street && ((Street) fieldArrayList.get(k)).getOwner() == player) {
                        localData.add((Street) fieldArrayList.get(k));
                    }
                }
            }
            if (!localData.isEmpty()) {
                map.put(localData.get(0).getColor(), localData.toArray(Street[]::new));
                localData.clear();
                propertiesToUpgrade.addAll(localData);
            }

        }
        return map;
    }

    public Map<String, Street[]> buildEqual(Map<String, Street[]> sort) {
        int minVal = 5;
        ArrayList<Street> sortedStreet = new ArrayList<>();
        HashMap<String, Street[]> buildingMap = new HashMap<>();
        for (Map.Entry<String, Street[]> entry : sort.entrySet()) {
            for (int i = 0; i < entry.getValue().length; i++) {
                if (entry.getValue()[i].getHouseAmount() < minVal) {
                    minVal = entry.getValue()[i].getHouseAmount();
                }
            }
            if (minVal != 5) {
                for (int i = 0; i < entry.getValue().length; i++) {
                    if (entry.getValue()[i].getHouseAmount() == minVal) {
                        sortedStreet.add(entry.getValue()[i]);
                    }
                }
                if (!sortedStreet.isEmpty()) {
                    buildingMap.put(entry.getKey(), sortedStreet.toArray(Street[]::new));
                    sortedStreet.clear();
                }
            }
        }
        return buildingMap;
    }

    public Map<String, Street[]> propertyWithBuilding(Map<String, Street[]> checkProps) {
        int minVal = 0;
        ArrayList<Street> sortedStreet = new ArrayList<>();
        HashMap<String, Street[]> buildingMap = new HashMap<>();
        for (Map.Entry<String, Street[]> entry : checkProps.entrySet()) {
            for (int i = 0; i < entry.getValue().length; i++) {
                if (entry.getValue()[i].getHouseAmount() != minVal) {
                    sortedStreet.add(entry.getValue()[i]);
                }
            }
            if (!sortedStreet.isEmpty()) {
                buildingMap.put(entry.getKey(), sortedStreet.toArray(Street[]::new));
                sortedStreet.clear();
            }
        }
        return buildingMap;
    }

    public void addBuilding(Street property) {
        if (property.getHouseAmount() < 4) {
            property.setHouseAmount(property.getHouseAmount() + 1);
            property.getOwner().setBalance(-property.getHousePrice());
            setHousePool(getHousePool() - 1);
        } else if (property.getHouseAmount() == 4) {
            property.setHouseAmount(property.getHouseAmount() + 1);
            property.getOwner().setBalance(-property.getHousePrice());
            setHousePool(getHousePool() + 4);
            property.setHotel(true);
            setHotelPool(getHotelPool() - 1);
        }
    }

    public Street getStreetFromString(String street) {
        Street field = null;
        for (int i = 0; i < fieldArrayList.size(); i++) {
            if (fieldArrayList.get(i).getName().equals(street)) {
                field = (Street) fieldArrayList.get(i);
            }
        }
        return field;
    }

    public int getHousePool() {
        return housePool;
    }

    public int getHotelPool() {
        return hotelPool;
    }

    public void setHousePool(int housePool) {
        this.housePool = housePool;
    }

    public void setHotelPool(int hotelPool) {
        this.hotelPool = hotelPool;
    }

    public void sellBuilding(Street property, int amountToSell) {
        if (property.isHotel()) {
            property.setHouseAmount(property.getHouseAmount() - 5);
            property.getOwner().setBalance((property.getHousePrice() / 2) * 5);
            property.setHotel(false);
            setHotelPool(hotelPool + 1);
        } else {
            property.setHouseAmount(property.getHouseAmount() - amountToSell);
            property.getOwner().setBalance((property.getHousePrice() / 2) * amountToSell);
            setHousePool(housePool + amountToSell);
        }
    }

    public int[] housesAndHotelsOwned(Player player) {
        int houses = 0;
        int hotels = 0;
        for (Field field : fieldArrayList) {
            if (field instanceof Street && ((Street) field).getOwner() != null && ((Street) field).getOwner().equals(player)) {
                if (((Street) field).isHotel()) hotels++;
                else houses += ((Street) field).getHouseAmount();
            }
        }
        return new int[]{houses, hotels};
    }

    public Property[] removePlayer(Player player) {
        ArrayList<Property> properties = new ArrayList<>();
        for (Field field : fieldArrayList
        ) {
            if (field instanceof Property && ((Property) field).getOwner() == player) {
                ((Property) field).setOwner(null);
                properties.add((Property) field);
            }
        }
        return properties.toArray(Property[]::new);
    }
    public boolean sell(Player affectedPlayer, int price, IGameStateDTO gameState){
        Map<String, Street[]> ownsGroup = ownsColourGroup(affectedPlayer);
        while(!affectedPlayer.setBalance(price)){
            Map<String, Street[]> buildingsToSell = propertyWithBuilding(ownsGroup);
            if (buildingsToSell.size() == 0) {

                return false;
            } else {
                //Find the properties the player can sell for
                String colorChosen = gameState.getGuiController().selectColorBuild(Language.getInstance().getLanguageValue("sellBuildingText"), buildingsToSell.keySet().toArray(String[]::new));
                int sellPrice =  buildingsToSell.get(colorChosen)[0].getHousePrice() / 2;
                String whereToSell = gameState.getGuiController().selectBuild(Language.getInstance().getLanguageValue("buildingPrice", Integer.toString( sellPrice),Integer.toString( sellPrice*5)), buildingsToSell.get(colorChosen));
                Street target = getStreetFromString(whereToSell);
                if (target.isHotel()) {
                    sellBuilding(getStreetFromString(whereToSell), 0);
                    gameState.getGuiController().guiRemoveHotel(getStreetFromString(whereToSell));
                } else {
                    gameState.getGuiController().guiAddHouse(target, target.getHouseAmount()-1);
                }
                affectedPlayer.setBalance(target.getHousePrice()/2);
                gameState.getGuiController().updatePlayer(affectedPlayer);
            }
        }
        return true;
    }
}


