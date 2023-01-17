package models.chanceCards;

import controllers.PlayerController;
import controllers.StartValues;
import models.Player;
import models.dto.IGameStateDTO;

public class MoveToField extends ChanceCard{

    private final int fieldId;
    private final boolean passStartBonus;

    /**
     * Constructor for the Chance-cards that move the player to a specific field.
     * @param name        Must match a key in the language hashmap
     * @param description Must be imported from the language hashmap
     * @param fieldId   The name of the field to move to
     */
    public MoveToField(String name, String description, boolean passStartBonus, int fieldId) {
        super(name, description);
        this.passStartBonus = passStartBonus;
        this.fieldId = fieldId;
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState){
        int boardSize = StartValues.getInstance().getValue("boardSize");
        gameState.getGuiController().showChanceCard(description);
        PlayerController playerController = gameState.getPlayerController();
        Player activePlayer = gameState.getActivePlayer();

        if(!(passStartBonus)){
            activePlayer.setLocation(fieldId);
            if(fieldId == 10){
                gameState.getFieldController().jailPlayer(activePlayer);
            }else {
                gameState.getFieldController().landOnField(gameState);
            }
            gameState.getGuiController().movePlayer(activePlayer, gameState.isReverse());
        }else {
            int spacesToMove;
            if (gameState.isReverse() && fieldId > activePlayer.getLocation()) {
                spacesToMove = (fieldId - (boardSize + activePlayer.getLocation()));
            } else if (!gameState.isReverse() && fieldId < activePlayer.getLocation()) {
                spacesToMove = (fieldId + boardSize) - activePlayer.getLocation();
            } else {
                spacesToMove = (fieldId - activePlayer.getLocation());
            }
            playerController.playerMove(activePlayer, spacesToMove);
            gameState.getGuiController().movePlayer(activePlayer, gameState.isReverse());
            gameState.getFieldController().landOnField(gameState);
        }
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
