package chanceCards;

public class MoveToField extends ChanceCard{

    private String fieldName;

    /**
     * Constructor for the Chancecards that move the player to a specific field.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     * @param FieldName   The name of the field to move to
     */
    public MoveToField(String Name, String Description, String FieldName) {
        super(Name, Description);
        this.fieldName = FieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
