package ui;

import controllers.FieldController;
import controllers.GUIConverter;
import controllers.PlayerController;
import controllers.StartValues;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import models.Language;
import models.Player;
import models.fields.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GUIController {
    private GUI gui;
    private GUI_Player[] gui_players;
    public GUIController(){
    }

    /**
     * Create a gui with custom fields
     * @param fieldList List of fields
     */
    public GUIController(ArrayList<Field> fieldList){
        gui = new GUI(GUIConverter.fieldListToGUI(fieldList));
    }
    public int getBid(String msg, int min, int max){
        return gui.getUserInteger(msg, min, max);
    }

    /**
     * Ask the user the amount of players in this game session. Min 2, max 4
     * @param playerAmountText Text to ask players how many are player
     * @return PlayerAmount :  How many players are going to play the game
     */
    public int playerAmount(String playerAmountText){
        return gui.getUserInteger(playerAmountText, StartValues.getInstance().getValue("minPlayers"), StartValues.getInstance().getValue("maxPlayers"));
    }


    /**
     * @param selectCharacterText Text to display asking user to choose their character
     * @param choices Character choices available to them
     * @return The character in string format
     */
    public String selectCharacter(String selectCharacterText, String choices){
        return gui.getUserSelection(selectCharacterText, choices.split(","));
    }

    /**
     * Insert the players into the GUI
     * @param players Players in the game
     */
    public void setPlayers(Player[] players){
        gui_players = GUIConverter.playerToGUI(players);
        for (GUI_Player player : gui_players) {
            gui.addPlayer(player);
        }
    }



    /**
     * Display a message and get the okay from the player
     * @param msg Message to display
     */
    public void displayMsg(String msg){
        gui.getUserButtonPressed(msg, "ok");
    }
    public String getName(String getNameText){
        return gui.getUserString(getNameText);
    }

    /**
     * Update a player on the board
     * @param player Player to be updated
     */
    public void updatePlayer(Player player){
        gui_players[player.getID()].setBalance(player.getBalance());
        gui_players[player.getID()].getCar().setPosition(gui.getFields()[player.getLocation()]);
    }

    /**
     * Update the player to move to a new field
     * @param player Player to be moved. Simulates movement by moving the gui mirror of player 1 step at a time.
     */
    public void movePlayer(Player player, boolean reverse){
        int direction = reverse ? -1 : 1;
        int carIndex = Arrays.asList(gui.getFields()).indexOf(gui_players[player.getID()].getCar().getPosition());
            while(carIndex != player.getLocation()){
                if (reverse) {
                    carIndex = carIndex-1;
                    if (carIndex < 0) {
                        carIndex = 39;
                    }
                } else {
                    carIndex = (carIndex+1)%40;
                }
                gui_players[player.getID()].getCar().setPosition(gui.getFields()[carIndex]);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        updatePlayer(player);
    }

    


    /**
     * @param rolls Array of ints. Can show up to two dice
     */
    public void displayDice(int[] rolls) {
        if(rolls.length == 1){
            gui.setDice(rolls[0],0);
        }else{
            gui.setDice(rolls[0],rolls[1]);
        }
    }

    /**
     * Set the owner on the property
     * @param property Property to be changed
     */
    public void updateField(Property property, FieldController fieldController) {
        GUI_Ownable field = (GUI_Ownable) gui.getFields()[property.getID()];
        if (property instanceof Street) {
            if (((Street) property).isHotel()) {
                ((GUI_Street) field).setHotel(true);
                field.setRent(String.valueOf(((Street) property).getRent()[5]));
            } else {
                ((GUI_Street) field).setHouses(((Street) property).getHouseAmount());
                field.setRent(String.valueOf(((Street) property).getRent()[((Street) property).getHouseAmount()]));
            }
        } else if (property instanceof Ferry) {
            int ferriesOwned = fieldController.ferrysOwned(property.getOwner(), property.getID(), 4) - 1;
            field.setRent(String.valueOf(((Ferry) property).getRent()[ferriesOwned]));
        } else if (property instanceof Brewery) {
            int breweriesOwned = fieldController.breweriesOwned(property.getOwner(), property.getID()) - 1;
            if (breweriesOwned > 0) {
                field.setRent(((Brewery) property).getRent1() + " gange terningslag");
            } else field.setRent(((Brewery) property).getRent0() + " gange terningslag");
        }
        field.setOwnerName(property.getOwner().getIdentifier());
        if (property.getOwner() == null) {
            field.setBorder(Color.black);
        } else {
            field.setBorder(property.getOwner().getCharacter().getColor());
        }
    }

    public void displayMsgNoBtn(String msg){
        gui.showMessage(msg);
    }

    public void showChanceCard(String message){
        gui.displayChanceCard(message);
    }

    public void endGame(){gui.close();}

    public void getRoll(String rollText, String rollButton) {
        gui.getUserButtonPressed(rollText, rollButton);
    }


    /**
     * Iterates through every player and every field and updates the gui
     * @param playerController provides the players
     * @param fieldController provides the fields
     */
    public void updateBoard(PlayerController playerController, FieldController fieldController){
        for (Player player : playerController.getPlayers()) {
            updatePlayer(player);
        }
        for (Field field: fieldController.getFieldList()) {
            if ((field instanceof Street || field instanceof Brewery || field instanceof Ferry) && ((Property) field).getOwner() != null){
                updateField((Property) field, fieldController);
            }
        }
    }

    public Boolean getUserLeftButtonPressed(java.lang.String msg, java.lang.String trueButton, java.lang.String falseButton) {
        return gui.getUserLeftButtonPressed(msg, trueButton, falseButton);
    }

    /**
     *
     * @param canPay boolean representing if the player can afford to pay to get out of jail.
     * @param hasChanceCard boolean representing if the player has a get out of jail card
     * @return the updated game state
     */


    public String getOutOfJailOptions(boolean canPay, boolean hasChanceCard) {
        String message = Language.getInstance().getLanguageValue("getOutOfJail");
        String pay = Language.getInstance().getLanguageValue("payOutOfJail", "1000");
        String roll = Language.getInstance().getLanguageValue("rollOutOfJail");
        String card = Language.getInstance().getLanguageValue("cardOutOfJail");
        String choice;
        if (canPay && hasChanceCard) {
            choice = gui.getUserSelection(message, roll, pay, card);
        } else if(canPay){
            choice = gui.getUserSelection(message, roll, pay);
        } else if (hasChanceCard) {
            choice = gui.getUserSelection(message, roll,card);
        } else {
            choice = gui.getUserSelection(message,roll);
        }


        if (choice.equals(card)) {
            return "card";
        } else if (choice.equals(pay)) {
            return "pay";
        } else {
            return "roll";
        }
    }
    public void getOutOfJailRollAgain(){
        String msg = Language.getInstance().getLanguageValue("getOutOfJailRollAgain");
        gui.getUserButtonPressed(msg,"ok");
    }

    public String selectBuild(String selectBuild, Street[] choices){
        String[] strings = Arrays.stream(choices).map((x -> x.getName())).toArray(String[]::new);
        return gui.getUserSelection(selectBuild, strings);
    }

    public void guiAddHouse(Street property, int amount){
        GUI_Street street = (GUI_Street) gui.getFields()[property.getID()];
        street.setHouses(amount);
    }
    public void guiAddHotel(Street property){
        GUI_Street street = (GUI_Street) gui.getFields()[property.getID()];
        street.setHotel(true);
    }
    public void guiRemoveHotel(Street property){
        GUI_Street street = (GUI_Street) gui.getFields()[property.getID()];
        street.setHotel(false);
    }
    public String selectColorBuild(String chooseColorOptions, String[] color){
        return gui.getUserSelection(chooseColorOptions,color);
    }

    public void removePlayer(Player player, Property[] properties) {
        gui_players[player.getID()].getCar().setPosition(null);
        for (int i = 0; i < properties.length; i++) {
            ((GUI_Ownable) gui.getFields()[properties[i].getID()]).setBorder(Color.black);
            ((GUI_Ownable) gui.getFields()[properties[i].getID()]).setOwnerName(null);
        }
    }
}


