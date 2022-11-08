package chanceCards;

public class MoveXSteps extends ChanceCard{

    private int minSteps;
    private int maxSteps;

    /**
     * Constructor for the Chancecards that allow the player to move from 1 to X steps.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public MoveXSteps(String Name, String Description) {
        super(Name, Description);
    }
}
