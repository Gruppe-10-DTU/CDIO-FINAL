package models.chanceCards;

import models.Player;
import models.dto.IGameStateDTO;

public class MoveToFerry extends ChanceCard{

    private final int rentMultiplier;
    private final boolean passStartBonus;

    public MoveToFerry(String name, String description, int rentMultiplier, boolean passStartBonus) {
        super(name, description);
        this.rentMultiplier = rentMultiplier;
        this.passStartBonus = passStartBonus;
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState){
        gameState.getGuiController().showChanceCard(this.description);
        Player player = gameState.getActivePlayer();
        int distToFerry = gameState.getFieldController().distToFirstFerry(player, gameState.isReverse());

        if(passStartBonus) {
            gameState.getPlayerController().playerMove(player, distToFerry);
        }else player.setLocation(player.getLocation() + distToFerry);

        gameState.getGuiController().movePlayer(player, gameState.isReverse());

        if(rentMultiplier == 1){
            gameState.getFieldController().landOnField(gameState);
        }else {
            gameState.getFieldController().landOnField(gameState, rentMultiplier);
        }
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
