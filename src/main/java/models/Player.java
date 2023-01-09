package models;

import models.chanceCards.GetOutOfJail;

/**
 * Player class
 */
public class Player {
    private final int iD;
    private final Balance balance;
    private String identifier;
    private Character character;
    private int soldSign = 12;
    private int location = 0;
    private GetOutOfJail getOutOfJail;
    private int roundsInJail;
    /**
     * @param iD   id of the player
     * @param name name
     * @default Balance is set to 20, soldSign set to 12, location set to 0, character not set
     */
    public Player(int iD, String name){
        this.iD = iD;
        this.identifier = name;
        balance = new Balance(20);
    }
    /**
     * @param iD        id of the player
     * @param name      name
     * @param character The player character
     * @default Balance is set to 20, location set to 0 and soldSign set to 12
     */
    public Player(int iD, String name, Character character){
        this.iD = iD;
        this.identifier = name;
        this.character = character;
        balance = new Balance(20);
    }

    /**
     * @param iD              id of the player
     * @param name            Name of player
     * @param startingBalance starting balance
     * @param character       Player character
     * @defaults set to 12 by default, location set to 0
     */
    public Player(int iD, String name, int startingBalance, Character character){
        this.iD = iD;
        identifier = name;
        balance = new Balance(startingBalance);
        this.character = character;
    }

    /**
     * @param iD              ID of player
     * @param name            Name of player
     * @param startingBalance Starting bank account value
     * @param character       Players character token
     * @param soldSign        Amount of properties they can own
     * @defaults location set to 0
     */

    public Player(int iD, String name, int startingBalance, Character character, int soldSign){
        this.iD = iD;
        identifier = name;
        balance = new Balance(startingBalance);
        this.character = character;
        this.soldSign = soldSign;
    }

    /**
     * @return Balance of player
     */
    // getter
    public int getBalance() {
        return balance.getBalance();
    }

    /**
     * @param newBalance The change in balance for the player. Cannot be set below 0
     * @return True if change was possible, false if balance wasn't changed
     */
    // setter
    public boolean setBalance(int newBalance) {
        int newValue = balance.getBalance() + newBalance;
        if(newValue>=0){
            balance.setBalance(newValue);
            return true;
        }
        return false;
    }

    /**
     * @return Name of player
     */
    // getter
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param newIdentifier Set name of player
     */
    // setter
    public void setIdentifier(String newIdentifier) {
        this.identifier = newIdentifier;
    }

    /**
     * @return Get soldSigns left
     */
    public int getSoldSign() {
        return soldSign;
    }

    /**
     * @param character New character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * @return Character of the player
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Decrease sold sign by one after the player buys a property.
     * @return Return true if soldSign is above or equal to 0 otherwise return false
     */
    public boolean decreaseSoldSign(){
        if(this.soldSign<=0){
            return false;
        }
        this.soldSign--;
        return true;
    }

    /**
     * Increase soldSign by one. Cannot increase if at 12 already due to rules.
     * @return boolean showing if the increase was possible
     */
    public boolean increaseSolgSign(){
        if(this.soldSign>=12){
            return false;
        }
        this.soldSign++;
        return true;
    }

    public int getLocation() {
        return location;
    }

    /**
     * @param location
     * @param newLocation
     */
    public void setLocation(int location, int newLocation) {
        this.location = (location + newLocation);
    }

    public int getID() {
        return iD;
    }

    public int setID(int newID) {return newID;}

    public void setLocation(int location) {
        this.location = location;
    }

    public GetOutOfJail getGetOutOfJail() {
        return getOutOfJail;
    }

    public void setGetOutOfJail(GetOutOfJail getOutOfJail) {
        this.getOutOfJail = getOutOfJail;
    }

    public int getRoundsInJail() {
        return roundsInJail;
    }

    public void setRoundsInJail(int roundsInJail) {
        this.roundsInJail = roundsInJail;
    }
}
