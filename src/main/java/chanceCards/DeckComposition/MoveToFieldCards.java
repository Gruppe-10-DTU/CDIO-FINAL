package chanceCards.DeckComposition;

public enum MoveToFieldCards {
    MOVE_TO_FIELD_1("START"),
    MOVE_TO_FIELD_2("SKATE_PARK"),
    MOVE_TO_FIELD_3("BEACH_WALK");

    private final String fieldName;
    MoveToFieldCards(String FieldName){
        this.fieldName = FieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
