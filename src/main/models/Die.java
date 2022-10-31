package game.models;

public class Die {
    private int sides = 6;

    public int roll() {
        int diceRoll = (int) Math.floor(Math.random() * sides + 1);
        return diceRoll;
    }
}
