package models.chanceCards;

import controllers.PlayerController;
import models.Player;
import models.dto.IGameStateDTO;

public class MoveXSteps extends ChanceCard{

    private final int MAX_STEPS;

    /**
     *
     * @param Name card name
     * @param Description card text
     * @param StepsToMove how many steps the card moves the player
     */
         public MoveXSteps(String Name, String Description, int StepsToMove) {
        super(Name, Description);

        this.MAX_STEPS = StepsToMove;
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState) {
        int direction = 1;
        if (gameState.isReverse()) direction = -1;
        PlayerController playerController = gameState.getPlayerController();
        Player activePlayer = gameState.getActivePlayer();
        gameState.getGuiController().showChanceCard(this.description);
        playerController.playerMove(activePlayer, this.MAX_STEPS * direction);
        if (MAX_STEPS < 0) {
            gameState.getGuiController().movePlayer(activePlayer, !gameState.isReverse());
        } else {
            gameState.getGuiController().movePlayer(activePlayer, gameState.isReverse());
        }
        gameState.getFieldController().landOnField(gameState);
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
