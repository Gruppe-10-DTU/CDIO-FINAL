package models.chanceCards;

import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

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

    @Override
    public GameStateDTO chanceEffect(GameStateDTO gameState){
        throw new NotImplementedException();
    }


    public int getMaxSteps() {
        return MAX_STEPS;
    }
}
