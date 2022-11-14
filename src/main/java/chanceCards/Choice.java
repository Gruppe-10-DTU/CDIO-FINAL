package chanceCards;

public class Choice extends ChanceCard{

    private final int MOVE;
    private final boolean DRAW_AGAIN;

    /**
     * Constructor for the Chancecards that leave the player with a choice
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public Choice(String Name, String Description, int MOVE, boolean DrawAgain) {
        super(Name, Description);
        this.MOVE = MOVE;
        this.DRAW_AGAIN = DrawAgain;
    }
}
