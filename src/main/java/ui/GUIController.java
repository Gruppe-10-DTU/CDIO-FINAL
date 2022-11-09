package ui;

import gui_main.GUI;

public class GUIController {
    private GUI gui;
    public GUIController(){
        gui = new GUI();

    }

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


