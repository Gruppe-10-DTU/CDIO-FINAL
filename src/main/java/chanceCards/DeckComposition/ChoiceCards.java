package chanceCards.DeckComposition;

public enum ChoiceCards {
    CHOICE_1( 1, true);

    private final int effect;
    private final boolean drawAgain;

    ChoiceCards(int EffectValue, boolean DrawAgain) {
        this.effect = EffectValue;
        this.drawAgain = DrawAgain;
    }


    public int getEffect() {
        return effect;
    }

    public boolean getDrawAgain() {
        return drawAgain;
    }
}
