package controllers;

import models.*;
import org.apache.commons.io.output.StringBuilderWriter;
import ui.GUIController;

import java.util.*;

public class GameController {
    private DiceHolder diceHolder = new DiceHolder();
    private int turnCounter = 0;
    private Language language;
    private GUIController guiController;
    private PlayerController playerController;
    public GameController(){
        language = new Language(System.getProperty("user.language"));
        guiController = new GUIController();
        int playerAmount = guiController.playerAmount();
        playerController = new PlayerController(playerAmount);
        String name;
        String originalName = "car, racecar, ufo, tractor";
        String[] splitName = originalName.split(",");
        StringBuilder sb = new StringBuilder("Car,Tractor,Racecar,UFO");

        for (int i = 0; i < playerAmount; i++) {

            name = guiController.getName("inputName");
            while(!playerController.playerUnique(name)){
                guiController.displayError("nameNotUnique");
                name = guiController.getName("inputName");
            }

            String character = guiController.selectCharacter("selectCharacter", String.valueOf(sb));
            sb.delete(sb.indexOf(character),sb.indexOf(character)+character.length()+1);

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
    public String checkAllBalance() {
        List<String> equalLS = new ArrayList<>();
        Player[] players = playerController.getPlayers();
        String winner;
        checkEqualBalance(equalLS, players);
        if (equalLS.size() > 1) {
            winner = findMaxTotalBalance(equalLS, players);
        } else {
            winner = findMaxBalance(players);
        }
        return winner;
    }

    /**
     * Finds and returns the player with the biggest balance
     * @return
     */
    private String findMaxBalance(Player[] players) {
        int currMax = 0;
        String currLeader = "";
        for (Player player : players) {
            if (player.getBalance() > currMax) {
                currMax = player.getBalance();
                currLeader = player.getIdentifier();
            }
        }
        return currLeader;
    }

    /**
     * Finds the maximum total value of the players with the same balance
     * @param equalLS
     * @return
     */
    private String findMaxTotalBalance(List<String> equalLS, Player[] players) {
        HashMap<Player, Integer> playerProp = fieldController.playerPropertyValues();
        Player winner = null;
        int maxTotal = 0;
        for (Player player : players) {
            int playerBal = player.getBalance();
            if(maxTotal < playerBal && equalLS.contains(player.getIdentifier())){
                maxTotal = playerBal;
                winner = player;
            }
        }
        return winner != null ? winner.getIdentifier() : null;
    }

    /**
     * Adds players to the list if they have the same balance
     * @param equalLS
     */
    public void checkEqualBalance(List<String> equalLS, Player[] players) {
        for (Player playerFst : players) {
            for (Player playerNxt : players) {
                if (playerFst.getBalance() == playerNxt.getBalance() && !playerFst.getIdentifier().equals(playerNxt.getIdentifier()) &&  !equalLS.contains(playerNxt.getIdentifier())) {
                    equalLS.add(playerNxt.getIdentifier());
                }
            }
        }
    }
}
