package controllers;
import models.chanceCards.CharacterSpecific;
import models.*;
import models.Character;
import models.fields.Start;

public class PlayerController {
    //make int Amount variable until GUI controller complete
    private Player[] players;
    public PlayerController(int playerAmount) {
        players = new Player[playerAmount];
    }
    /**
     * Adds a new player to the game.
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
     * Move designated player x amount of spaces, and check if they passed start.
     * @param player
     * Player-class input : Designate what player you want to move.
     * @param spaces
     * Moves designated player x amount of spaces from current position.
     * @return
     */
    public Player playerMove(Player player, int spaces){
        int oldLocation = player.getLocation();
        if(oldLocation + spaces >= 40){
            player.setLocation(oldLocation, spaces);
            player.setLocation(player.getLocation(),-40);
            player.setBalance(4000);
        }else{
            player.setLocation(oldLocation,spaces);
        }
        return player;
    }
    public int stickyLocation(Player player, int Integer){
        int oldLocation = player.getLocation()-Integer;
        return oldLocation;
    }

    /**
     * @param iD will apply modulus so it'll avoid outOfBoundsError
     * @return Player with the relevant id
     */
    public Player getPlayerById(int iD){
        return players[iD % players.length];
    }

    public Player[] getPlayers() {
        return players;
    }

    /**
     * Compares player name and checks if it is unique.
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




    /**
     * Checks if given player can afford to pay rent of specified square, then transfers money.
     * @param player : Player-class. Who is renting the place?
     * @param property : Property-class. Property in mention.
     * @return
     */
    public boolean getRent(Player player, Start.Property property, boolean doubleRent) {
        int rent = doubleRent ? property.getPrice() * 2 : property.getPrice();
        if (property.getOwner() == player) {
            return true;
        } else {
            if (player.setBalance(-rent)) {
                property.getOwner().setBalance(rent);
                return true;
            }
            return false;
        }
    }

    /**
     * gives the player a character specific chance card
     * @param CharacterName pass
     */
    public void addCharacterCard(CharacterSpecific CharacterName){
        for (Player player : players) {
            if (player.getCharacter().getName().equals(CharacterName.getCharacter())){
                player.setCharacterSpecific(CharacterName);
            }
        }
    }
}
