package controllers;

import models.*;
import ui.GUIController;

public class GameController {
    private DiceHolder diceHolder = new DiceHolder();
    private int turnCounter = 0;
    private Language language;
    private GUIController guiController;
    public GameController(){
        guiController = new GUIController();
        int playerAmount = guiController.playerAmount();
    }
    /*
    //Turn function for the roll button
    public boolean turn(){
        Player player = players[turnCounter % 2];
        boolean returnValue = player.setBalance(fields[diceHolder.sum()-2].getEffect());
        //If the player hasn't won and didn't roll 10, increase the turn
        if(!hasWon() && diceHolder.sum() != 10){
            turnCounter++;
        }
        return returnValue;
    }

     */
    public int sum(){
        return diceHolder.sum() - 1;
    }

    public Integer getTurnCounter() {
        return turnCounter;
    }
}
