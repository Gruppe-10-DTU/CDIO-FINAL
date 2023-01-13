package models.chanceCards;

import controllers.PlayerController;
import models.Player;
import models.dto.IGameStateDTO;

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
    public void chanceEffect(IGameStateDTO gameState){
        PlayerController playerController = gameState.getPlayerController();
        Player activePlayer = gameState.getActivePlayer();
        gameState.getGuiController().showChanceCard(this.description);
        playerController.playerMove(activePlayer, this.MAX_STEPS);
        gameState.getGuiController().movePlayer(activePlayer);
        gameState.getFieldController().landOnField(gameState);
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
