package models.chanceCards;

import models.dto.IGameStateDTO;
import models.Player;

public class Grant extends ChanceCard{

    private final int bonus;
    private final int netWorth;
    public Grant(String name, String description, int netWorth, int bonus) {
        super(name, description);
        this.bonus = bonus;
        this.netWorth = netWorth;
    }
    @Override
    public void chanceEffect(IGameStateDTO gameState){
        gameState.getGuiController().showChanceCard(this.description);
        Player player = gameState.getActivePlayer();
        int playerWorth = player.getBalance() + gameState.getFieldController().playerPropertyValues(player);
        if(playerWorth < netWorth){
            gameState.getGuiController().displayMsg("Du modtager matador-legatet");
            player.setBalance(bonus);
        }
        gameState.getGuiController().updatePlayer(player);
        gameState.getChanceCardDeck().returnToDeck(this);
    }
}
