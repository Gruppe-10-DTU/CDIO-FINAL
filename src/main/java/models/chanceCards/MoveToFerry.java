package models.chanceCards;

public class MoveToFerry extends ChanceCard{

    private final int RENT_MULTIPLIER;
    private final boolean PASS_START_BONUS;

    public MoveToFerry(String NAME, String Description, int RentMultiplier, boolean PassStartBonus) {
        super(NAME, Description);
        this.RENT_MULTIPLIER = RentMultiplier;
        this.PASS_START_BONUS = PassStartBonus;
    }

    public int getRENT_MULTIPLIER() {
        return RENT_MULTIPLIER;
    }

    public boolean getPASS_START_BONUS() {
        return PASS_START_BONUS;
    }
}
