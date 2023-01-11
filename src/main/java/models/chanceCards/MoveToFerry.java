package models.chanceCards;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class MoveToFerry extends ChanceCard{

    private final int RENT_MULTIPLIER;
    private final boolean PASS_START_BONUS;

    public MoveToFerry(String NAME, String Description, int RentMultiplier, boolean PassStartBonus) {
        super(NAME, Description);
        this.RENT_MULTIPLIER = RentMultiplier;
        this.PASS_START_BONUS = PassStartBonus;
    }

    @Override
    public GameStateDTO chanceEffect(GameStateDTO gameState){
        gameState.getGuiController().showChanceCard(this.description);
        Player player = gameState.getActivePlayer();
        int distToFerry = gameState.getFieldController().distToFirstFerry(player);

        if(PASS_START_BONUS) {
            gameState.getPlayerController().playerMove(player, distToFerry);
        }else player.setLocation(player.getLocation() + distToFerry);

        gameState.getGuiController().updatePlayer(player);

        if(RENT_MULTIPLIER > 1){
            gameState.getFieldController().landOnField(gameState);
        }else{
            for (int i = 0; i < RENT_MULTIPLIER; i++) {
                gameState.getFieldController().landOnField(gameState);
            }
        }

        gameState.getChancecardDeck().returnToDeck(this);

        return gameState;
    }
}
