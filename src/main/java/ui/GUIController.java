package ui;

import controllers.GUIConverter;
import gui_fields.GUI_Player;
import gui_main.GUI;
import models.Player;
import models.fields.Field;

import java.util.ArrayList;

public class GUIController {
    private final GUI gui;
    private GUI_Player[] gui_players;
    public GUIController(){
        gui= new GUI();
    }
    public GUIController(ArrayList<Field> fieldList){

        gui = new GUI(GUIConverter.fieldListToGUI(fieldList));

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
     * @param player Player to be moved
     */
    public void movePlayer(Player player){
        gui_players[player.getID()].getCar().setPosition(gui.getFields()[player.getLocation()]);
    }
}


