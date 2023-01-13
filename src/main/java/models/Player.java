package models;

import models.chanceCards.GetOutOfJail;

import java.util.ArrayList;

/**
 * Player class
 */
public class Player {
    private final int iD;
    private final Balance balance;
    private String identifier;
    private Character character;
    private int location = 0;
    private ArrayList<GetOutOfJail> getOutOfJail = new ArrayList<>();
    private int roundsInJail;
    /**
     * @param iD   id of the player
     * @param name name
     * @default Balance is set to 30000, soldSign set to 12, location set to 0, character not set
     */
    public Player(int iD, String name){
        this.iD = iD;
        this.identifier = name;
        balance = new Balance(30000);
    }

    /**
     * @param iD              id of the player
     * @param name            Name of player
     * @param startingBalance starting balance
     * @param character       Player character
     */
    public Player(int iD, String name, int startingBalance, Character character){
        this.iD = iD;
        identifier = name;
        balance = new Balance(startingBalance);
        this.character = character;
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
     * @return Character of the player
     */
    public Character getCharacter() {
        return character;
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
    public void setLocation(int location) {
        this.location = location;
    }

    public boolean hasGetOutOfJail() {
        return getOutOfJail.size() > 0;
    }

    public void addGetOutOfJail(GetOutOfJail getOutOfJail) {
        this.getOutOfJail.add(getOutOfJail);
    }
    public GetOutOfJail useGetOutOfJail(){
        this.setRoundsInJail(0);
        GetOutOfJail card = this.getOutOfJail.get(0);
        this.getOutOfJail.remove(0);
        return card;
    }

    public int getRoundsInJail() {
        return roundsInJail;
    }

    public void setRoundsInJail(int roundsInJail) {
        this.roundsInJail = roundsInJail;
    }
    public void stayInJail(){
        this.roundsInJail++;
    }
}
