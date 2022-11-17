package chanceCards;

public class MoveToField extends ChanceCard{

    private final int FIELD_ID;

    /**
     * Constructor for the Chancecards that move the player to a specific field.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     * @param FIELD_ID   The name of the field to move to
     */
    public MoveToField(String Name, String Description, int FIELD_ID) {
        super(Name, Description);
        this.FIELD_ID = FIELD_ID;
    }

    public int getFieldID() {
        return FIELD_ID;
    }
}
