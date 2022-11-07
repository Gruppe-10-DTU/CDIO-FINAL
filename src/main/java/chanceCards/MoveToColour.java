package chanceCards;

import java.awt.*;

public class MoveToColour extends ChanceCard{

    private Color colour_1;
    private Color colour_2;

    /**
     * Constructor for the Chancecards that move the player to a given colour.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public MoveToColour(String Name, String Description, Color Colour_1, Color Colour_2){
        super(Name, Description);
        this.colour_1 = Colour_1;
        this.colour_2 = Colour_2;
    }
}
