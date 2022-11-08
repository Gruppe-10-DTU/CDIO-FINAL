package chanceCards;

public class CharacterSpecific extends ChanceCard{

    private Character character;

    /**
     * Constructor for the Chancecards that are specific to a given character.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public CharacterSpecific(String Name, String Description) {
        super(Name, Description);
    }
}
