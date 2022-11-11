package controllers;
import models.*;
import models.Character;
import ui.GUIController;

public class PlayerController {
    //make int Amount variable until GUI controller complete
    private Player[] players;
    public PlayerController(int playerAmount) {
        players = new Player[playerAmount];
    }
    /**
     * @param player
     * Primitive int type. From x amount of players in controller, select y player.
     * @param characterName
     * String type. Takes characterName for available characters, e.i. Tractor, racecar, etc.
     * @param name
     * String type. Takes players personal identifier e.i. their name, nickname, callingID, etc.
     * @return
     */
    public void addPlayer(int player, String characterName, String name){
        Character ch = new Character(characterName, "");
        players[player] = new Player(player,name,20-2*(players.length-2),ch );
    }

    /**
     * @param player
     * Non-primitive Player-class input. Designate what player you want to move.
     * @param spaces
     * Primitive int input. Moves designated player x amount of spaces from current position.
     * @return
     */
    public Player playerMove(Player player, int spaces){
        int oldLocation = player.getLocation();
        if(oldLocation + spaces >= 24){
            player.setLocation(oldLocation, spaces);
            player.setLocation(player.getLocation(),-24);
            player.setBalance(2);
        }else{
            player.setLocation(oldLocation,spaces);
        }
        return player;
    }

    public Player[] getPlayers() {
        return players;
    }

    /**
     * Checks if the plyaers name is going to be unique
     * @param name Name of new player
     * @return true if name is unique, otherwise false
     */
    public boolean playerUnique(String name){
        for(Player player : players){
            if(player == null) return true;
            if(player.getIdentifier().equals(name)){
                return false;
            }
        }
        return true;
    }
}
