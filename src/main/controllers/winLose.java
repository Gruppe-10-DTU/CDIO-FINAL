package main.controllers;

import main.models.Player;

import java.util.ArrayList;
import java.util.List;

public class winLose {

    List<Player> players = new ArrayList<>();

    /**
     * Checks if either one player has the most balance, or checks all players with equal balance for the one with the most value in properties
     */
    public void checkAllBalance() {
        List<String> equalLS = new ArrayList<>();
        checkEqualBalance(equalLS);

        if (equalLS.size() > 1) {
            findMaxTotalBalance();
        } else {
            String winner = findMaxBalance();
        }
    }

    /**
     * Finds and returns the player with the biggest balance
     * @return
     */
    private String findMaxBalance() {
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


    private void findMaxTotalBalance() {

    }

    /**
     * Adds players to the list if they have the same balance
     * @param equalLS
     */
    public void checkEqualBalance(List<String> equalLS) {
        for (Player playerFst : players) {
            for (Player playerNxt : players) {
                if (playerFst.getBalance() == playerNxt.getBalance() && !playerFst.getIdentifier().equals(playerNxt.getIdentifier()) &&  !equalLS.contains(playerNxt.getIdentifier())) {
                    equalLS.add(playerNxt.getIdentifier());
                }
            }
        }
    }

}

