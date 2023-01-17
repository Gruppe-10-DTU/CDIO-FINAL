package models.chanceCards;

import controllers.PlayerController;
import models.Player;
import models.dto.IGameStateDTO;

public class MoveXSteps extends ChanceCard{

    private final int maxSteps;

    /**
     *
     * @param name card name
     * @param description card text
     * @param maxSteps how many steps the card moves the player
     */
         public MoveXSteps(String name, String description, int maxSteps) {
        super(name, description);

        this.maxSteps = maxSteps;
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState) {
        int direction = 1;
        if (gameState.isReverse()) direction = -1;
        PlayerController playerController = gameState.getPlayerController();
        Player activePlayer = gameState.getActivePlayer();
        gameState.getGuiController().showChanceCard(this.description);
        playerController.playerMove(activePlayer, this.maxSteps * direction);
        if (maxSteps < 0) {
            gameState.getGuiController().movePlayer(activePlayer, !gameState.isReverse());
        } else {
            gameState.getGuiController().movePlayer(activePlayer, gameState.isReverse());
        }
        gameState.getFieldController().landOnField(gameState);
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
