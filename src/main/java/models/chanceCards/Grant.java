package models.chanceCards;

public class Grant extends ChanceCard{

    private int bonus;
    private int netWorth;
    public Grant(String NAME, String Description, int MaximumPlayerWorth, int AwardsBonus) {
        super(NAME, Description);
        this.bonus = AwardsBonus;
        this.netWorth = MaximumPlayerWorth;
    }

    public int getBonus() {
        return bonus;
    }

    public int getNetWorth() {
        return netWorth;
    }
}
