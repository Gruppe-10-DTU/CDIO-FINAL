package ui;

import gui_main.GUI;

public class GUIController {
    private GUI gui;
    public GUIController(){
        gui = new GUI();

    }

    public int playerAmount(){
        int playerAmount = gui.getUserInteger("Please input amount of players",2, 4);
        return playerAmount;
    }



}


