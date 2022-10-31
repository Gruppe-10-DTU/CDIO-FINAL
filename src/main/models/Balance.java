package game.models;

public class Balance {
    private int balance = 1000;

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
