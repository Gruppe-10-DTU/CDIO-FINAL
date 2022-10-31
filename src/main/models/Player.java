package game.models;

public class Player {

    private final Balance balance = new Balance();
    private String identifier;

    //Constructor
    public Player(String name){
        identifier = name;
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
