package main.models;

/**
 * Player class
 */
public class Player {

    private final Balance balance;
    private String identifier;
    private Character character;

    /**
     * @param name Name of player
     * @param startingBalance starting balance
     * @param character Player character
     */
    public Player(String name, int startingBalance, Character character){
        identifier = name;
        balance = new Balance(startingBalance);
        this.character = character;
    }

    /**
     * @param name name
     * @param character The player character
     * @balance is set to 20 by default
     */
    public Player(String name, Character character){
        this.identifier = name;
        this.character = character;
        balance = new Balance(20);
    }

    // getter
    public int getBalance() {
        return balance.getBalance();
    }

    /**
     * @param newBalance The change in balance for the player
     * @return True if change was possible, false if it was set to 0
     */
    // setter
    public boolean setBalance(int newBalance) {
        int newValue = balance.getBalance() + newBalance;
        balance.setBalance(Math.max(newValue, 0));
        return newValue>=0;
    }

    // getter
    public String getIdentifier() {
        return identifier;
    }
    // setter
    public void setIdentifier(String newIdentifier) {
        this.identifier = newIdentifier;
    }
}
