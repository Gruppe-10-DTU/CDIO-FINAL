package chanceCards;

public class ChangeBalance extends ChanceCard{

    private int effect;

    /**
     * Constructor for the Chancecards that have an effect on the players money.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChangeBalance(String Name, String Description) {
        super(Name, Description);
    }
}
