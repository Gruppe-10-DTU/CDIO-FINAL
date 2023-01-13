package models.chanceCards;

import models.Player;
import models.dto.IGameStateDTO;

public class MoveToFerry extends ChanceCard{

    private final int RENT_MULTIPLIER;
    private final boolean PASS_START_BONUS;

    public MoveToFerry(String NAME, String Description, int RentMultiplier, boolean PassStartBonus) {
        super(NAME, Description);
        this.RENT_MULTIPLIER = RentMultiplier;
        this.PASS_START_BONUS = PassStartBonus;
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState){
        gameState.getGuiController().showChanceCard(this.description);
        Player player = gameState.getActivePlayer();
        int distToFerry = gameState.getFieldController().distToFirstFerry(player);

        if(PASS_START_BONUS) {
            gameState.getPlayerController().playerMove(player, distToFerry);
        }else player.setLocation(player.getLocation() + distToFerry);

        gameState.getGuiController().movePlayer(player);

        if(RENT_MULTIPLIER == 1){
            gameState.getFieldController().landOnField(gameState);
        }else {
            gameState.getFieldController().landOnField(gameState, RENT_MULTIPLIER);
        }
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
