package models.chanceCards;

public class MoveXSteps extends ChanceCard{

    private final int MAX_STEPS;

    /**
     *
     * @param Name
     * @param Description
     * @param StepsToMove
     */
         public MoveXSteps(String Name, String Description, int StepsToMove) {
        super(Name, Description);

        this.MAX_STEPS = StepsToMove;
    }


    public int getMaxSteps() {
        return MAX_STEPS;
    }
}
