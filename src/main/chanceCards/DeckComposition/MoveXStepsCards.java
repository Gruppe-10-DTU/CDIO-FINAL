package chanceCards.DeckComposition;

public enum MoveXStepsCards {
    MOVE_X_STEPS_1(1,5);

    private final int minValue;
    private final int maxValue;

    MoveXStepsCards(int MinimumValue, int MaximumValue){
        this.minValue = MinimumValue;
        this.maxValue = MaximumValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
