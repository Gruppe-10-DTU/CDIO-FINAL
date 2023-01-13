package models;

public class Balance {
    private int balance;

    /**
     * Set starting balance value
     * @param balance integer value
     */
    public Balance(int balance) {
        this.balance = balance;
    }

    /**
     * Instance with default value of 20
     */
    public int getBalance(){
        return balance;
    }
    public void setBalance(int newValue){
        balance = newValue;
    }
}
