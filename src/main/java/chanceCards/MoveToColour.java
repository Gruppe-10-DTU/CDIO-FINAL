package chanceCards;

import java.awt.*;

public class MoveToColour extends ChanceCard{

    private String colour_1;
    private String colour_2;

    /**
     * Constructor for the Chancecards that move the player to a given colour.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public MoveToColour(String Name, String Description, String Colour_1, String Colour_2){
        super(Name, Description);
        this.colour_1 = Colour_1;
        this.colour_2 = Colour_2;
    }

    public String getColour_1() {
        return colour_1;
    }

    public String getColour_2() {
        return colour_2;
    }
}
