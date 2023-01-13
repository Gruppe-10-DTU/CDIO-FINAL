package controllers;

import models.*;
import models.Character;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class PlayerController {
    //make int Amount variable until GUI controller complete
    public PlayerController() {
    }
    private  LinkedHashMap<Integer, Player> availablePlayers = new LinkedHashMap<>();

    /**
     * Adds a new player to the game.
     * @param player
     * Primitive int type. From x amount of players in controller, select y player.
     * @param characterName
     * String type. Takes characterName for available characters, e.i. Tractor, racecar, etc.
     * @param name
     * String type. Takes players personal identifier e.i. their name, nickname, callingID, etc.
     *
     */
    public void addPlayer(int player, String characterName, String name, int color){
        Character ch = new Character(characterName, color);
        Player playerNow = new Player(player,name,StartValues.getInstance().getValue("startingMoney"), ch);
        availablePlayers.put(player, playerNow);
    }
    /**
     * Removes a player from the game.
     * @param player
     * Player ID. Who do you want to remove?
     */
    public void removePlayer(int player){
        availablePlayers.remove(player);
    }

    /**
     * Move designated player x amount of spaces, and check if they passed start.
     * @param player
     * Player-class input : Designate what player you want to move.
     * @param spaces
     * Moves designated player x amount of spaces from current position.
     */
    public Player playerMove(Player player, int spaces){
        int oldLocation = player.getLocation();
        //if(spaces + oldLocation < 0){
            //spaces += StartValues.getInstance().getValue("boardSize");
        //}
        if(oldLocation + spaces >= StartValues.getInstance().getValue("boardSize")) {
            player.setLocation(oldLocation, spaces);
            player.setPreviousLocation(player.getLocation());
            player.setLocation(player.getLocation(), -StartValues.getInstance().getValue("boardSize"));
            player.setBalance(StartValues.getInstance().getValue("passStartBonus"));
        } else if (oldLocation + spaces < 0) {
            spaces += StartValues.getInstance().getValue("boardSize");
            player.setPreviousLocation(player.getLocation());
            player.setLocation(oldLocation, spaces);
            if (player.getPreviousLocation() > 0) {
                player.setBalance(StartValues.getInstance().getValue("passStartBonus"));
            }
        }else{
            player.setPreviousLocation(player.getLocation());
            player.setLocation(oldLocation,spaces);
        }
    }

    /**
     * @param iD will apply modulus so it'll avoid outOfBoundsError
     * @return Player with the relevant id
     */
    public Player getPlayerById(int iD){
        return availablePlayers.get(iD);
    }

    public Player[] getPlayers() {
        return availablePlayers.values().toArray(Player[]::new);
    }

    public ArrayList<Player> otherPlayers(int playerId){
        return availablePlayers.values().stream().filter(x-> x.getID() != playerId).collect(Collectors.toCollection(ArrayList::new));
    }
    /**
     * Compares player name and checks if it is unique.
     * @param name Name of new player
     * @return true if name is unique, otherwise false
     */
    public boolean playerUnique(String name){
        for(Player player : availablePlayers.values()){
            if(player == null) return true;
            if(player.getIdentifier().equals(name)){
                return false;
            }
        }
        return true;
    }
}
