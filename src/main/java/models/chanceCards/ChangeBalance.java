package models.chanceCards;

import models.Player;
import models.dto.IGameStateDTO;

public class ChangeBalance extends ChanceCard{

    private final int effect;
    private final boolean fromOthers;

    /**
     * Constructor for the ChanceCards that have an effect on the player's money.
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public ChangeBalance(String name, String description, int effect, boolean fromOthers) {
        super(name, description);
        this.effect = effect;
        this.fromOthers = fromOthers;
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState){
        Player currentPlayer = gameState.getActivePlayer();
        int total = 0;
        gameState.getGuiController().showChanceCard(description);
        if (fromOthers) {
            for (Player otherPlayer : gameState.getOtherPlayers()) {
                if (otherPlayer.setBalance(effect * -1) || gameState.getFieldController().sell(otherPlayer, effect, gameState)){
                    total += effect;
                }
            }
        }else total = effect;
        if (!currentPlayer.setBalance(total) || !gameState.getFieldController().sell(currentPlayer, effect, gameState)) {
            gameState.getGuiController().displayMsg("Du har ikke penge nok til at betale bøden og må derfor forlade spillet");
            gameState.getPlayerController().removePlayer(currentPlayer.getID());
        }
        gameState.getChanceCardDeck().returnToDeck(this);
    }

}
