package models.chanceCards;

public class MoveToFerry extends ChanceCard{

    private int rentMultiplier;
    private boolean passStartBonus;

    public MoveToFerry(String NAME, String Description, int RentMultiplier, boolean PassStartBonus) {
        super(NAME, Description);
        this.rentMultiplier = RentMultiplier;
        this.passStartBonus = PassStartBonus;
    }

    public int getRentMultiplier() {
        return rentMultiplier;
    }

    public boolean isPassStartBonus() {
        return passStartBonus;
    }
}
