package chanceCards.DeckComposition;

public enum CharacterSpecificCards {
    CHARACTER_SPECIFIC_1( "Car", true),
    CHARACTER_SPECIFIC_2("Ship", true),
    CHARACTER_SPECIFIC_3( "Dog", true),
    CHARACTER_SPECIFIC_4( "Cat", true);


    private final String charName;
    private final boolean drawAgain;

    CharacterSpecificCards(String CharacterName, boolean DrawAnotherCard){
        this.charName = CharacterName;
        this.drawAgain = DrawAnotherCard;
    }

    public String getCharName() {
        return charName;
    }
}
