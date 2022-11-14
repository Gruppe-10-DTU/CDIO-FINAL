package main.controllers;

import main.models.Player;

import java.util.ArrayList;
import java.util.List;

public class winLose {

    List<Player> players = new ArrayList<>();

    /**
     * Checks if either one player has the most balance, or checks all players with equal balance for the one with the most value in properties
     */
    public String checkAllBalance() {
        List<String> equalLS = new ArrayList<>();
        String winner;
        checkEqualBalance(equalLS);
        if (equalLS.size() > 1) {
            winner = findMaxTotalBalance(equalLS);
        } else {
            winner = findMaxBalance();
        }
        return winner;
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

    /**
     * Finds the maximum total value of the players with the same balance
     * @param equalLS
     * @return
     */
    private String findMaxTotalBalance(List<String> equalLS) {
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

