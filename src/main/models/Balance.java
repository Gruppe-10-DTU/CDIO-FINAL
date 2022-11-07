package main.models;

public class Balance {
    private int balance = 20;
    public Balance(int balance) {
        this.balance = balance;
    }
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
