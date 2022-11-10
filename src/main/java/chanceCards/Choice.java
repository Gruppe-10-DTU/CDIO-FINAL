package chanceCards;

public class Choice extends ChanceCard{

    private final int move;
    private final boolean drawAgain;

    /**
     * Constructor for the Chancecards that leave the player with a choice
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public Choice(String Name, String Description, int Move, boolean DrawAgain) {
        super(Name, Description);
        this.move = Move;
        this.drawAgain = DrawAgain;
    }
}
