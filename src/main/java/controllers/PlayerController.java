package controllers;
import models.*;
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
     * @param character
     * Non-primitive Character class type. Select which of the available characters player is assigned.
     * @param name
     * Non-primitive String type. Set player identifier.
     * @return
     */
    public void addPlayer(int player, Character character, String name){
        players[player] = new Player("Player" + player);
        players[player].setIdentifier(name);
        players[player].setBalance(20-2*(players.length-2));
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

}
