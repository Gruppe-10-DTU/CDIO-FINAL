package main.chanceCards;

public class MoveToColour extends ChanceCard{

    private String[] colourTargets;

    /**
     * Constructor for the Chancecards that move the player to a given colour.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public MoveToColour(String Name, String Description) {
        super(Name, Description);
    }
}
