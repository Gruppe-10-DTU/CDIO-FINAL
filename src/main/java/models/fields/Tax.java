package models.fields;

import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Tax extends Field{

    public Tax(String name, int iD, int priceValue, int priceProcent) {
        this.name = name;
        this.iD = iD;
        this.priceValue = priceValue;
        this.priceProcent = priceProcent;
    }

    private int priceValue;

    private int priceProcent;

    public int getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(int priceValue) {
        this.priceValue = priceValue;
    }

    public int getPriceProcent() {
        return priceProcent;
    }

    public void setPriceProcent(int priceProcent) {
        this.priceProcent = priceProcent;
    }

    @Override
    public GameStateDTO fieldEffect(GameStateDTO gameState){
        if(priceProcent==0){
            if(gameState.getActivePlayer().setBalance(priceValue*-1)){
                gameState.getGuiController().displayMsg("Du skal betale skal på "+priceValue);
            }else{
                gameState.getPlayerController().removePlayer(gameState.getActivePlayer().getID());
            }
        }else{
            if(gameState.getGuiController().getUserLeftButtonPressed("Du skal betale ejendomsskat. Vil du betale 4000 eller 10% af din ejendomsværdi?", "4000", "10%")){
                if(gameState.getActivePlayer().setBalance(priceValue*-1)){
                    gameState.getGuiController().displayMsg("Du betalte "+priceValue+" i skat.");
                }else{
                    //Auktion
                    gameState.getGuiController().displayMsg("Du havde ikke råd til at betale og bliver fjernet");
                    gameState.getPlayerController().removePlayer(gameState.getActivePlayer().getID());
                }
            }else{
                int totalAmount = Math.round((gameState.getFieldController().playerPropertyValues(gameState.getActivePlayer()) + gameState.getActivePlayer().getBalance())*(priceProcent/100.0f));
                if(gameState.getActivePlayer().setBalance(totalAmount*-1)){
                    gameState.getGuiController().displayMsg("Du betalte "+totalAmount+" i skat for dine ejendomme.");
                }else{
                    //Auktion

                    gameState.getGuiController().displayMsg("Du havde ikke råd til at betale og bliver fjernet");
                    gameState.getPlayerController().removePlayer(gameState.getActivePlayer().getID());
                }
            }
        }
        return gameState;
    }
}
