package controllers;

import models.fields.Field;

import java.util.ArrayList;

public class GUIController extends ui.GUIController {
    public String[] names = {"test1","test2", "test3", "test4", "Test5"};
    public String[] characters = {"Car", "Tractor", "Racecar", "UFO"};
    int playerIndex = 0;

    public GUIController(){
        super();
    }

    /**
     * Create a gui with custom fields
     *
     * @param fieldList List of fields
     */
    public GUIController(ArrayList<Field> fieldList) {

    }

    /**
     * Ask the user the amount of players in this game session. Min 2, max 4
     *
     * @param playerAmountText Text to ask players how many are player
     * @return PlayerAmount :  How many players are going to play the game
     */
    @Override
    public int playerAmount(String playerAmountText) {
        return 3;
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
        return characters[playerIndex--];
    }


}
