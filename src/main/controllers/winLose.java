package main.controllers;

import main.models.Player;

import java.util.ArrayList;
import java.util.List;

public class winLose {

    List<Player> players = new ArrayList<>();

    public void checkAllBalance() {
        List<String> equalLS = new ArrayList<>();
        checkEqualBalance(equalLS);

        if (equalLS.size() > 1) {
            findMaxTotalBalance();
        } else {
            String winner = findMaxBalance();
        }
    }

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

    public void checkEqualBalance(List<String> equalLS) {
        for (Player playerFst : players) {
            for (Player playerNxt : players) {
                if (playerFst.getBalance() == playerNxt.getBalance() && !playerFst.getIdentifier().equals(playerNxt.getIdentifier())) {
                    equalLS.add(playerNxt.getIdentifier());
                }
            }
        }
    }

}

