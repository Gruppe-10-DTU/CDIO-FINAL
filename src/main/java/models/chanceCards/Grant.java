package models.chanceCards;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Grant extends ChanceCard{

    private final int BONUS;
    private final int NET_WORTH;
    public Grant(String NAME, String Description, int MaximumPlayerWorth, int AwardsBonus) {
        super(NAME, Description);
        this.BONUS = AwardsBonus;
        this.NET_WORTH = MaximumPlayerWorth;
    }
    @Override
    public GameStateDTO chanceEffect(GameStateDTO gameState){
        gameState.getGuiController().showChanceCard(this.description);
        Player player = gameState.getActivePlayer();
        int playerWorth = player.getBalance() + gameState.getFieldController().playerPropertyValues(player);
        if(playerWorth < NET_WORTH){
            gameState.getGuiController().displayMsg("Du modtager matador-legatet");
            player.setBalance(BONUS);
        }
        gameState.getGuiController().updatePlayer(player);
        gameState.getChancecardDeck().returnToDeck(this);

        return gameState;
    }
}
