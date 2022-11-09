package chanceCards;

public enum DeckComposition {
    CHARACTER_SPECIFIC_1("CharacterSpecific"),
    CHARACTER_SPECIFIC_2("CharacterSpecific"),
    CHARACTER_SPECIFIC_3("CharacterSpecific"),
    CHARACTER_SPECIFIC_4("CharacterSpecific"),

    MOVE_TO_COLOUR_1("MoveToColour"),
    MOVE_TO_COLOUR_2("MoveToColour"),
    MOVE_TO_COLOUR_3("MoveToColour"),
    MOVE_TO_COLOUR_4("MoveToColour"),
    MOVE_TO_COLOUR_5("MoveToColour"),
    MOVE_TO_COLOUR_6("MoveToColour"),
    MOVE_TO_COLOUR_7("MoveToColour"),

    MOVE_TO_FIELD_1("MoveToField"),
    MOVE_TO_FIELD_2("MoveToField"),
    MOVE_TO_FIELD_3("MoveToField"),

    GET_OUT_OF_JAIL("GetOutOfJail"),

    CHANGE_BALANCE_1("ChangeBalance"),
    CHANGE_BALANCE_2("ChangeBalance"),
    CHANGE_BALANCE_3("ChangeBalance"),

    CHOICE_1("Choice"),

    MOVE_X_STEPS("MoveXSteps");

    private final String type;

    DeckComposition(String Type){
        this.type = Type;
    }

    public String getType() {
        return type;
    }
}
