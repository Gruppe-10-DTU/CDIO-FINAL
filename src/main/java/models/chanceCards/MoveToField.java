package models.chanceCards;

import controllers.PlayerController;
import controllers.StartValues;
import models.Player;
import models.dto.GameStateDTO;

public class MoveToField extends ChanceCard{

    private final int FIELD_ID;
    private final boolean PASS_START_BONUS;

    /**
     * Constructor for the Chancecards that move the player to a specific field.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     * @param FIELD_ID   The name of the field to move to
     */
    public MoveToField(String Name, String Description, boolean PassStartBonus, int FIELD_ID) {
        super(Name, Description);
        this.PASS_START_BONUS = PassStartBonus;
        this.FIELD_ID = FIELD_ID;
    }

    @Override
    public GameStateDTO chanceEffect(GameStateDTO gameState){
        gameState.getGuiController().showChanceCard(description);
        PlayerController playerController = gameState.getPlayerController();
        Player activePlayer = gameState.getActivePlayer();

        if(!(PASS_START_BONUS)){
            activePlayer.setLocation(FIELD_ID);
            if(FIELD_ID == 10){
                gameState.getFieldController().jailPlayer(activePlayer);
            }else {
                gameState.getFieldController().landOnField(gameState);
            }
        }else{
            int spacesToMove = FIELD_ID - activePlayer.getLocation();
            if(spacesToMove < 0) spacesToMove += StartValues.getInstance().getValue("boardSize");
            playerController.playerMove(activePlayer,spacesToMove);
            gameState.getFieldController().landOnField(gameState);
        }
        gameState.getChancecardDeck().returnToDeck(this);
        return gameState;
    }
}
