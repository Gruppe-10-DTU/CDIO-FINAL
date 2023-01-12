package models.chanceCards;

import models.dto.IGameStateDTO;

public class GetOutOfJail extends ChanceCard{

    /**
     * Constructor for the get out of jail free card
     * @param Name        Must match a key in the language hashmap
     * @param Description Must be imported from the language hashmap
     */
    public GetOutOfJail(String Name, String Description) {
        super(Name, Description);
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState){
        if (gameState.getFieldController().isJailed(gameState.getActivePlayer())) {
            gameState.getFieldController().freePlayer(gameState.getActivePlayer());
            gameState.getChancecardDeck().returnToDeck(this);
        }else{
            gameState.getGuiController().showChanceCard(this.description);
            gameState.getActivePlayer().addGetOutOfJail(this);
        }
    }
}
