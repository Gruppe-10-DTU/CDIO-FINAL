package controllers;
import models.Player;
public class PlayerController {
    public PlayerController() {
        Player player = new Player(0, "Player");
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
