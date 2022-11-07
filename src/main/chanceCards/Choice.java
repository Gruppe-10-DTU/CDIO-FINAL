package chanceCards;

public class Choice extends ChanceCard{

    /**
     * Constructor for the Chancecards that leave the player with a choice
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public Choice(String Name, String Description ) {
        super(Name, Description);
    }
}
