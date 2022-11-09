package ui;

import controllers.GUIConverter;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;
import models.Player;

import java.util.Arrays;

public class GUIController {
    private final GUI gui;
    public GUIController(){
        gui = new GUI();

    }

    /**
     * Ask the user the amount of players in this game session. Min 2, max 4
     * @return PlayerAmount :  How many players are going to play the game
     */
    public int playerAmount(){
        return gui.getUserInteger("Please input amount of players",2, 4);
    }


    /**
     * @param selectCharacterText Text to display asking user to choose their character
     * @param choices Character choices available to them
     * @return The character in string format
     */
    public String selectCharacter(String selectCharacterText, String choices){
        return gui.getUserSelection(selectCharacterText, choices.split(","));
    }

    public void setPlayers(Player[] players){
        for (GUI_Player player : GUIConverter.playerToGUI(players)) {
            gui.addPlayer(player);
        }
    }
    public void displayError(String error){
        gui.getUserButtonPressed(error, "ok");
    }
    public String getName(String getNameText){
        return gui.getUserString(getNameText);
    }

}


