package chanceCards;

public class MoveXSteps extends ChanceCard{

    private int minSteps;
    private int maxSteps;

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
}
