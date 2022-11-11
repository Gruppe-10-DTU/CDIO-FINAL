package chanceCards;

public class MoveXSteps extends ChanceCard{

    private final int MIN_STEPS;
    private final int MAX_STEPS;

    /**
     *
     * @param Name
     * @param Description
     * @param MinimumSteps
     * @param MaximumSteps
     */
         public MoveXSteps(String Name, String Description, int MinimumSteps , int MaximumSteps) {
        super(Name, Description);
        this.MIN_STEPS = MinimumSteps;
        this.MAX_STEPS = MaximumSteps;
    }

    public int getMinSteps() {
        return MIN_STEPS;
    }

    public int getMaxSteps() {
        return MAX_STEPS;
    }
}
