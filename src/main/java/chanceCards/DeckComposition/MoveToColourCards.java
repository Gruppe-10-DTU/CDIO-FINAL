package chanceCards.DeckComposition;

import java.awt.*;

public enum MoveToColourCards{
    MOVE_TO_COLOUR_1(Color.ORANGE, null),
    MOVE_TO_COLOUR_2(Color.CYAN, null),
    MOVE_TO_COLOUR_3(Color.RED, null),
    MOVE_TO_COLOUR_4(Color.ORANGE , Color.GREEN),
    MOVE_TO_COLOUR_5(Color.PINK , Color.BLUE),
    MOVE_TO_COLOUR_6(Color.CYAN , Color.RED),
    MOVE_TO_COLOUR_7(Color.YELLOW , Color.BLACK);

    private final Color colour1;
    private final Color colour2;

    MoveToColourCards(Color Colour1, Color Colour2){
        this.colour1 = Colour1;
        this.colour2 = Colour2;
    }

    public Color getColour1() {
        return colour1;
    }

    public Color getColour2() {
        return colour2;
    }
}
