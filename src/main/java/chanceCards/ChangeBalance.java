package chanceCards;

public class ChangeBalance extends ChanceCard{

    private final int EFFECT;

    /**
     * Constructor for the Chancecards that have an effect on the players money.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChangeBalance(String Name, String Description, int EFFECT) {
        super(Name, Description);
        this.EFFECT = EFFECT;
    }

    public int getEffect() {
        return EFFECT;
    }
}
