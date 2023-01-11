package controllers;

import models.Player;
import models.fields.Field;
import models.fields.Property;
import models.fields.Street;

import java.util.ArrayList;

public class GUIControllerStub extends ui.GUIController {
    public String[] names = {"test1","test2", "test3", "test4", "Test5"};
    public String[] characters = {"Car", "Tractor", "Racecar", "UFO"};
    int playerIndex = 0;
    private boolean buttonClicked;

    public void setButtonClicked(boolean buttonClicked) {
        this.buttonClicked = buttonClicked;
    }

    public GUIControllerStub(){
        super();
    }

    /**
     * Create a gui with custom fields
     *
     * @param fieldList List of fields
     */
    public GUIControllerStub(ArrayList<Field> fieldList) {

    }

    /**
     * Ask the user the amount of players in this game session. Min 2, max 4
     *
     * @param playerAmountText Text to ask players how many are player
     * @return PlayerAmount :  How many players are going to play the game
     */
    @Override
    public int playerAmount(String playerAmountText) {
        return 2;
    }

    @Override
    public String getName(String getNameText) {
        return names[playerIndex];
    }

    /**
     * @param selectCharacterText Text to display asking user to choose their character
     * @param choices             Character choices available to them
     * @return The character in string format
     */
    @Override
    public String selectCharacter(String selectCharacterText, String choices) {
        return characters[playerIndex++];
    }

    /**
     * Insert the players into the GUI
     *
     * @param players Players in the game
     */
    @Override
    public void setPlayers(Player[] players) {

    }

    /**
     * Update a player on the board
     *
     * @param player Player to be updated
     */
    @Override
    public void updatePlayer(Player player) {

    }

    /**
     * Update the player to move to a new field
     *
     * @param player Player to be moved
     */
    @Override
    public void movePlayer(Player player) {

    }

    @Override
    public void getRoll(String rollText, String rollButton) {

    }
    @Override
    public void displayDice(int[] rolls) {

    }

    /**
     * Display a message and get the okay from the player
     *
     * @param msg Message to display
     */
    @Override
    public void displayMsg(String msg) {

    }

    /**
     * Set the owner on the property
     *
     * @param property Property to be changed
     */
    @Override
    public void updateField(Property property) {

    }

    /**
     * @param emtpyFieldChoice Text to display
     * @param properties       The choices you have
     */
    @Override
    public int getPropertyChoice(String emtpyFieldChoice, Street[] properties) {
        return 23;
    }

    public void showChanceCard(String message){

    }

    @Override
    public Boolean getUserLeftButtonPressed(String msg, String trueButton, String falseButton) {
        return buttonClicked;
    }

    @Override
    public void endGame() {
    }

    @Override
    public String getOutOfJailOptions(boolean canPay, boolean hasChanceCard) {
        if(canPay) return "pay";
        else if (hasChanceCard) return "card";
        else return "roll";
    }

    @Override
    public void displayMsgNoBtn(String msg) {

    }
}
