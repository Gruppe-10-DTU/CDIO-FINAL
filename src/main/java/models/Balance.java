package models;

public class Balance {
    private int balance = 20;

    /**
     * Set starting balance value
     * @param balance
     */
    public Balance(int balance) {
        this.balance = balance;
    }

    /**
     * Instanciate with default value of 20
     */
    public Balance() {

    }
    public int getBalance(){
        return balance;
    }
    public void setBalance(int newValue){
        balance = newValue;
    }
    public boolean checkBalance(){
        return balance < 0;
    }
}
