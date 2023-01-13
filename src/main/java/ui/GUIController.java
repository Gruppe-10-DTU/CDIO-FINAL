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

import java.util.ArrayList;
import java.util.Arrays;

public class GUIController {
    private GUI gui;
    private Language language;
    private GUI_Player[] gui_players;
    public GUIController(){
    }

    /**
     * Create a gui with custom fields
     * @param fieldList List of fields
     */
    public GUIController(ArrayList<Field> fieldList, Language language){
        gui = new GUI(GUIConverter.fieldListToGUI(fieldList));
        this.language = language;
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
            // player.getCar().setPosition(gui.getFields()[0]);
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
     * @param player Player to be moved. Simulates movement by moving the gui mirror of player 1 stpe at a time.
     */
    public void movePlayer(Player player){
        int carIndex = Arrays.asList(gui.getFields()).indexOf(gui_players[player.getID()].getCar().getPosition());
            while(carIndex != player.getLocation()){
                carIndex = (carIndex+1)%40;
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
    public void updateField(Property property){
        GUI_Ownable field = (GUI_Ownable) gui.getFields()[property.getID()];
   //     street.setOwnableLabel(property.getOwner().getIdentifier());
    //    street.setOwnerName(property.getOwner().getIdentifier());
        if(property instanceof Street){
            if (((Street) property).isHotel()) {
                ((GUI_Street) field).setHotel(true);
            }else {
                ((GUI_Street) field).setHouses(((Street) property).getHouseAmount());
            }
        }
        field.setOwnerName(property.getOwner().getIdentifier());
        field.setBorder(property.getOwner().getCharacter().getColor());
        //field.setSubText(property.getOwner().getIdentifier());
    }

    public void displayMsgNoBtn(String msg){
        gui.showMessage(msg);
    }

    public void showChanceCard(String message){
        gui.displayChanceCard(message);
    }
    public String showChanceCardChoice(String message, String option1, String option2){
        return gui.getUserSelection(message, option1,option2);
    }
    public int getXStepsToMove(String message, int MinVal, int MaxVal){
        return gui.getUserInteger(message,MinVal,MaxVal);
    }

    public void endGame(){gui.close();}

    public void getRoll(String rollText, String rollButton) {
        gui.getUserButtonPressed(rollText, rollButton);
    }

    /**
     * @param emtpyFieldChoice Text to display
     * @param properties The choices you have
     */
    public int getPropertyChoice(String emtpyFieldChoice, Street[] properties) {
        String choice = gui.getUserSelection(emtpyFieldChoice, Arrays.stream(properties).map(Field::getName).toArray(String[]::new));
        return Arrays.stream(properties).filter(field->field.getName()==choice).findFirst().get().getID();
    }

    /**
     * Iterates thrhrough every player and every field and updates the gui
     * @param playerController provides the players
     * @param fieldController provides the fields
     */
    public void updateBoard(PlayerController playerController, FieldController fieldController){
        for (Player player : playerController.getPlayers()) {
            updatePlayer(player);
        }
        for (Field field: fieldController.getFieldList()) {
            if ((field instanceof Street || field instanceof Brewery || field instanceof Ferry) && ((Property) field).getOwner() != null){
                updateField((Property) field);
            }
        }
    }

    public Boolean getUserLeftButtonPressed(java.lang.String msg, java.lang.String trueButton, java.lang.String falseButton) {
        boolean response = gui.getUserLeftButtonPressed(msg, trueButton, falseButton);
        return response;
    }

    /**
     *
     * @param canPay boolean representing if the player can afford to pay to get out of jail.
     * @param hasChanceCard boolean representing if the player has a get out of jail card
     * @return the updated gamestate
     */


    public String getOutOfJailOptions(boolean canPay, boolean hasChanceCard) {
        String message = language.getLanguageValue("getOutOfJail");
        String pay = language.getLanguageValue("payOutOfJail");
        String roll = language.getLanguageValue("rollOutOfJail");
        String card = language.getLanguageValue("cardOutOfJail");
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
        String msg = language.getLanguageValue("getOutOfJailRollAgain");
        gui.getUserButtonPressed(msg,"ok");
    }

    public String selectWhatToBuild(String buildingChoices, String choices){
        return gui.getUserSelection(buildingChoices, choices.split(","));
    }

    public String selectBuild(String selectBuild, Street[] choices){
        String[] strings = Arrays.stream(choices).map((x -> x.getName())).toArray(String[]::new);
        return gui.getUserSelection(selectBuild, strings);
    }

    public String amountOfHouses(String amountOfHouses){
        return gui.getUserString(amountOfHouses);
    }

    public boolean yesnoSelection(String yesorno){
        String yesno = "Yes,No";
        String choice = gui.getUserSelection(yesorno,yesno.split(","));
        if(choice.toLowerCase().equals("yes")){
            return true;
        }else{
            return false;
        }
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
    public int sellAmount(int minHouse, int maxHouse){
        return gui.getUserInteger("How many buildings do you wish to sell?", minHouse, maxHouse);
    }






}


