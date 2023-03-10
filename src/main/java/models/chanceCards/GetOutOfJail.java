package models.chanceCards;

import models.dto.IGameStateDTO;

public class GetOutOfJail extends ChanceCard{

    /**
     * Constructor for the get out of jail free card
     * @param name        Must match a key in the language hashmap
     * @param description Must be imported from the language hashmap
     */
    public GetOutOfJail(String name, String description) {
        super(name, description);
    }

    @Override
    public void chanceEffect(IGameStateDTO gameState){
        if (gameState.getFieldController().isJailed(gameState.getActivePlayer())) {
            gameState.getChanceCardDeck().returnToDeck(this);
        }else{
            gameState.getGuiController().showChanceCard(this.description);
            gameState.getActivePlayer().addGetOutOfJail(this);
        }
    }
}
