package models.fields;

import models.Player;
import models.dto.IGameStateDTO;
import models.Language;

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
                gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("landOnExtraTax","" + priceValue));
            }else{
                gameState.getPlayerController().removePlayer(currentPlayer.getID());
            }
        }else{
            if(gameState.getGuiController().getUserLeftButtonPressed(Language.getInstance().getLanguageValue("landOnIncomeTax"), "4000", "10%")){
                if(currentPlayer.setBalance(priceValue*-1) || gameState.getFieldController().sell(currentPlayer, priceValue, gameState)){
                    gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("payIncomeTaxFlat","" + priceValue));
                }else{
                    //Auktion
                    gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("disqualified"));
                    gameState.getPlayerController().removePlayer(currentPlayer.getID());
                }
            }else{
                int totalAmount = Math.round((gameState.getFieldController().playerPropertyValues(currentPlayer) + currentPlayer.getBalance())*(priceProcent/100.0f));
                if(currentPlayer.setBalance(totalAmount*-1) ||  gameState.getFieldController().sell(currentPlayer,-totalAmount, gameState)){
                    gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("payIncomeTaxPercentage","" + totalAmount));
                }else{
                    //Auktion
                    gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("disqualified"));
                    gameState.getPlayerController().removePlayer(currentPlayer.getID());
                }
            }
        }
    }
}
