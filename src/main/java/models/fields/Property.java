package models.fields;

import models.Player;
import models.dto.IGameStateDTO;
import org.apache.commons.lang.ArrayUtils;
import models.Language;

public abstract class Property extends Field{
    protected int price;
    protected Player owner;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Player getOwner() {return owner;}
    public void setOwner(Player owner) {this.owner = owner;}

    protected abstract int getRentAmount(IGameStateDTO gameState);

    @Override
    public void fieldEffect(IGameStateDTO gameState, int rentMultiplier) {
        Player currentPlayer = gameState.getActivePlayer();
        if (owner == null) {
            if (currentPlayer.getBalance() >= price) {
                boolean wantToBuy = gameState.getGuiController().getUserLeftButtonPressed(Language.getInstance().getLanguageValue("landOnField",name,"" + price), Language.getInstance().getLanguageValue("ja"), Language.getInstance().getLanguageValue("nej"));

                if (wantToBuy) {
                    owner = currentPlayer;
                    currentPlayer.setBalance(-price);
                } else {
                    this.auction(gameState);
                }
            } else {
                //Player cant buy (possibly give the player an option to sell other values and then buy?)
                gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("landOnFieldNoFunds",name,""+price));

                this.auction(gameState);
            }
        } else {

            int rentToPay = getRentAmount(gameState)*rentMultiplier;

            if (owner == currentPlayer) {
                gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("ownField"));

            } else if (gameState.getFieldController().isJailed(owner)) {
                gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("landOnFieldOwnerJailed",name,owner.getIdentifier()));

            } else if (currentPlayer.setBalance(-rentToPay) || gameState.getFieldController().sell(currentPlayer, -rentToPay, gameState)) {
                gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("landOnFieldOwned",name,owner.getIdentifier(),"" + rentToPay));

                owner.setBalance(rentToPay);
                gameState.getGuiController().updatePlayer(owner);
            } else {
                gameState.getGuiController().displayMsg(Language.getInstance().getLanguageValue("landOnFieldPlayerBankrupt",name,owner.getIdentifier()));
                //Cant pay the rent

                gameState.getPlayerController().removePlayer(currentPlayer.getID());
            }
        }
    }

    protected void auction(IGameStateDTO gameStateDTO){
        Player[] players = gameStateDTO.getPlayerController().getPlayers();
        //Get the player after the current player
        int index = (ArrayUtils.indexOf(players,gameStateDTO.getActivePlayer())+ 1) % players.length;
        //Previous player. One player have to buy the property
        int k= -1;
        int bid = 0;
        int breakoutCounter = 0;
        do{
            if(players[index].getBalance()>bid && gameStateDTO.getGuiController().getUserLeftButtonPressed(Language.getInstance().getLanguageValue("auctionAction",players[index].getIdentifier()),Language.getInstance().getLanguageValue("ja"), Language.getInstance().getLanguageValue("nej"))){
                bid = gameStateDTO.getGuiController().getBid(Language.getInstance().getLanguageValue("landOnFieldAuction","" + bid),  bid + 1, players[index].getBalance());
                k=index;
            }
            index = (index + 1) % players.length;
            breakoutCounter++;
        }while(k!=index && breakoutCounter<5000);
        if(bid!=0) {
            players[index].setBalance(-bid);
            this.owner = players[index];
        }
    }
}
