package main.models;

/**
 * Player class
 */
public class Player {

    private final Balance balance;
    private String identifier;
    private Character character;
    private int soldSign = 12;
    /**
     * @param name name
     * @defualts Balance is set to 20, soldSign set to 12, character not set
     *
     */
    public Player(String name){
        this.identifier = name;
        balance = new Balance(20);
    }
    /**
     * @param name name
     * @param character The player character
     * @defaults Balance is set to 20 and soldSign set to 12
     */
    public Player(String name, Character character){
        this.identifier = name;
        this.character = character;
        balance = new Balance(20);
        this.soldSign = 12;
    }

    /**
     * @param name Name of player
     * @param startingBalance starting balance
     * @param character Player character
     * @soldSign set to 12 by default
     */
    public Player(String name, int startingBalance, Character character){
        identifier = name;
        balance = new Balance(startingBalance);
        this.character = character;
        this.soldSign = 12;
    }


    public Player(String name, int startingBalance, Character character, int soldSign){
        identifier = name;
        balance = new Balance(startingBalance);
        this.character = character;
        this.soldSign = soldSign;
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

    public int getSoldSign() {
        return soldSign;
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
}
