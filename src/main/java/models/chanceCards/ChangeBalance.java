package models.chanceCards;

public class ChangeBalance extends ChanceCard{

    private final int EFFECT;
    private final boolean FROM_OTHERS;

    /**
     * Constructor for the Chancecards that have an effect on the players money.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChangeBalance(String Name, String Description, int EFFECT, boolean FromOtherPlayers) {
        super(Name, Description);
        this.EFFECT = EFFECT;
        this.FROM_OTHERS = FromOtherPlayers;
    }

    public int getEffect() {
        return EFFECT;
    }
    public boolean getFromOthers(){
        return FROM_OTHERS;
    }
}
