package models.chanceCards;

import models.Player;
import models.dto.GameStateDTO;

public class ChangeBalance extends ChanceCard{

    private final int EFFECT;
    private final boolean FROM_OTHERS;

    /**
     * Constructor for the Chancecards that have an effect on the players money.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChangeBalance(String Name, String Description, int EFFECT, boolean FromOtherPlayers) {
        super(Name, Description);
        this.EFFECT = EFFECT;
        this.FROM_OTHERS = FromOtherPlayers;
    }

    @Override
    public GameStateDTO chanceEffect(GameStateDTO gameState){
        Player currentPlayer = gameState.getActivePlayer();
        int total = 0;
        gameState.getGuiController().displayMsg(description);
        if (FROM_OTHERS){
            for (Player otherPlayer : gameState.getOtherPlayers()) {
                if (!otherPlayer.setBalance(EFFECT * -1)) {

                    gameState.getGuiController().displayMsg(otherPlayer.getIdentifier()+", du har ikke penge nok til at betale til" + currentPlayer.getIdentifier() + " og må derfor forlade spillet");

                    gameState.getPlayerController().removePlayer(currentPlayer.getID());
                }else total+= EFFECT;
            }
        } else total = EFFECT;

        if (!currentPlayer.setBalance(total)) {

            gameState.getGuiController().displayMsg("Du har ikke penge nok til at betale bøden og må derfor forlade spillet");

            gameState.getPlayerController().removePlayer(currentPlayer.getID());
        }
        gameState.getChancecardDeck().returnToDeck(this);
        return gameState;
    }
}
