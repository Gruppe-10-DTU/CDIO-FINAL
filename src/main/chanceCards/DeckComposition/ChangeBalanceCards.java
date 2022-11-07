package chanceCards.DeckComposition;

public enum ChangeBalanceCards {
    CHANGE_BALANCE_1(2,false),
    CHANGE_BALANCE_2(-2,false),
    CHANGE_BALANCE_3(1,true);

    private final int amount;
    private final boolean fromOtherPlayers;

    ChangeBalanceCards(int Amount, boolean FromOtherPlayers){
        this.amount = Amount;
        this.fromOtherPlayers = FromOtherPlayers;
    }

    public int getAmount() {
        return amount;
    }
}
