package controllers;

import models.*;
import org.apache.commons.io.output.StringBuilderWriter;
import ui.GUIController;

import java.util.Arrays;

public class GameController {
    private DiceHolder diceHolder = new DiceHolder();
    private int turnCounter = 0;
    private Language language;
    private GUIController guiController;
    private PlayerController playerController;
    public GameController(){


        guiController = new GUIController();
        int playerAmount = guiController.playerAmount();
        playerController = new PlayerController(playerAmount);
        String name;
        StringBuilder sb = new StringBuilder("Car,Tractor,Racecar,UFO");

        for (int i = 0; i < playerAmount; i++) {

            name = guiController.getName("Please enter your name");
            while(!playerController.playerUnique(name)){
                guiController.displayError("The name is not unique.");
                name = guiController.getName("Please enter your name");
            }
            String character = guiController.selectCharacter("Please select a character", String.valueOf(sb));
            sb.delete(sb.indexOf(character), sb.indexOf(",",sb.indexOf(character)) );
            playerController.addPlayer(i, character, name);
        }
        guiController.setPlayers(playerController.getPlayers());
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
