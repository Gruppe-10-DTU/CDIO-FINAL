package models.chanceCards;


public class MoveToColour extends ChanceCard{

    private final String COLOUR_1;
    private final String COLOUR_2;

    /**
     *
     * Constructor for the Chancecards that move the player to a given colour.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     * @param COLOUR_1 colour as string
     * @param COLOUR_2 colour as string
     */
    public MoveToColour(String Name, String Description, String COLOUR_1, String COLOUR_2){
        super(Name, Description);
        this.COLOUR_1 = COLOUR_1;
        this.COLOUR_2 = COLOUR_2;
    }

    public String getColour_1() {
        return COLOUR_1;
    }

    public String getColour_2() {
        return COLOUR_2;
    }
}
