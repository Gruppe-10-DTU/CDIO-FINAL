package main.models;

public class Player {

    private final Balance balance;
    private String identifier;

    //Constructor
    public Player(String name){
        identifier = name;
        balance = new Balance();
    }
    public Player(String name, int startingBalance){
        identifier = name;
        balance = new Balance(startingBalance);
    }

    // getter
    public int getBalance() {
        return balance.getBalance();
    }
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
