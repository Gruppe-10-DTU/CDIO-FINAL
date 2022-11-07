package main.chanceCards;

public abstract class ChanceCard {
    protected String name;
    protected String description;

    /**
     * Super constructor
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChanceCard(String Name, String Description){
        this.name = Name;
        this.description = Description;
    }
}
