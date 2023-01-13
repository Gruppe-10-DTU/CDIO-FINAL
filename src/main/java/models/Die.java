package models;

public class Die {
    private int sides = 6;

    public int roll() {
        return (int) Math.floor(Math.random() * sides + 1);
    }
}
