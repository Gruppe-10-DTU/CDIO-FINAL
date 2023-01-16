package models.chanceCards;

import models.Player;
import models.dto.IGameStateDTO;

public class ChangeBalance extends ChanceCard{

    private final int EFFECT;
    private final boolean FROM_OTHERS;

    /**
     * Constructor for the ChanceCards that have an effect on the player's money.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChangeBalance(String Name, String Description, int EFFECT, boolean FromOtherPlayers) {
        super(Name, Description);
        this.EFFECT = EFFECT;
        this.FROM_OTHERS = FromOtherPlayers;
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState){
        Player currentPlayer = gameState.getActivePlayer();
        int total = 0;
        gameState.getGuiController().showChanceCard(description);
        if (FROM_OTHERS){
            for (Player otherPlayer : gameState.getOtherPlayers()) {
                if (!otherPlayer.setBalance(EFFECT * -1)) {

                    gameState.getGuiController().displayMsg(otherPlayer.getIdentifier()+", du har ikke penge nok til at betale til" + currentPlayer.getIdentifier() + " og må derfor forlade spillet");

                    gameState.getPlayerController().removePlayer(gameState.getActivePlayer().getID());
                }else total+= EFFECT;
            }
        } else total = EFFECT;

        if (!currentPlayer.setBalance(total)) {

            gameState.getGuiController().displayMsg("Du har ikke penge nok til at betale bøden og må derfor forlade spillet");

            gameState.getPlayerController().removePlayer(gameState.getActivePlayer().getID());
        }
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
