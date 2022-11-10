package chanceCards;

public class CharacterSpecific extends ChanceCard{

    private final String character;

    /**
     * Constructor for the Chancecards that are specific to a given character.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public CharacterSpecific(String Name, String Description, String CharacterName) {
        super(Name, Description);
        this.character = CharacterName;
    }

    public String getCharacter() {
        return character;
    }
}
