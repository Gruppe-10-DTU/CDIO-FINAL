package models.fields;

import models.Player;
import models.dto.IGameStateDTO;

public class Tax extends Field{

    public Tax(String name, int iD, int priceValue, int priceProcent) {
        this.name = name;
        this.iD = iD;
        this.priceValue = priceValue;
        this.priceProcent = priceProcent;
    }

    private int priceValue;

    private int priceProcent;


    public void setPriceProcent(int priceProcent) {
        this.priceProcent = priceProcent;
    }

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier){
        Player currentPlayer = gameState.getActivePlayer();
        if(priceProcent==0){
            if(currentPlayer.setBalance(priceValue*-1)){
                gameState.getGuiController().displayMsg("Du skal betale skal på "+priceValue);
            }else{
                gameState.getPlayerController().removePlayer(currentPlayer.getID());
            }
        }else{
            if(gameState.getGuiController().getUserLeftButtonPressed("Du skal betale ejendomsskat. Vil du betale 4000 eller 10% af din ejendomsværdi?", "4000", "10%")){
                if(currentPlayer.setBalance(priceValue*-1) || gameState.getFieldController().sell(currentPlayer, priceValue, gameState)){
                    gameState.getGuiController().displayMsg("Du betalte "+priceValue+" i skat.");
                }else{
                    //Auktion
                    gameState.getGuiController().displayMsg("Du havde ikke råd til at betale og bliver fjernet");
                    gameState.getPlayerController().removePlayer(currentPlayer.getID());
                }
            }else{
                int totalAmount = Math.round((gameState.getFieldController().playerPropertyValues(currentPlayer) + currentPlayer.getBalance())*(priceProcent/100.0f));
                if(currentPlayer.setBalance(totalAmount*-1) ||  gameState.getFieldController().sell(currentPlayer,-totalAmount, gameState)){
                    gameState.getGuiController().displayMsg("Du betalte "+totalAmount+" i skat for dine ejendomme.");
                }else{
                    //Auktion
                    gameState.getGuiController().displayMsg("Du havde ikke råd til at betale og bliver fjernet");
                    gameState.getPlayerController().removePlayer(currentPlayer.getID());
                }
            }
        }
    }
}
