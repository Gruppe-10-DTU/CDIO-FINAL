package chanceCards;

public abstract class ChanceCard {
    protected final String name;
    protected final String description;

    /**
     * Super constructor
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChanceCard(String Name, String Description){
        this.name = Name;
        this.description = Description;
    }
    public String getType(){
        return this.getClass().toString().replaceAll("class chanceCards.", "");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
