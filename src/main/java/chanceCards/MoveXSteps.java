package chanceCards;

public class MoveXSteps extends ChanceCard{

    private final int minSteps;
    private final int maxSteps;

    /**
     *
     * @param Name
     * @param Description
     * @param MinimumSteps
     * @param MaximumSteps
     */
         public MoveXSteps(String Name, String Description, int MinimumSteps , int MaximumSteps) {
        super(Name, Description);
        this.minSteps = MinimumSteps;
        this.maxSteps = MaximumSteps;
    }

    public int getMinSteps() {
        return minSteps;
    }

    public int getMaxSteps() {
        return maxSteps;
    }
}
