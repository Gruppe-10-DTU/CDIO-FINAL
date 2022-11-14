package chanceCards;

public class MoveToField extends ChanceCard{

    private final String FIELD_NAME;

    /**
     * Constructor for the Chancecards that move the player to a specific field.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     * @param FIELD_NAME   The name of the field to move to
     */
    public MoveToField(String Name, String Description, String FIELD_NAME) {
        super(Name, Description);
        this.FIELD_NAME = FIELD_NAME;
    }

    public String getFieldName() {
        return FIELD_NAME;
    }
}
