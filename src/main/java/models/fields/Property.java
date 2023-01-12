package models.fields;

import models.Player;
import models.dto.IGameStateDTO;
import org.apache.commons.lang.ArrayUtils;

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

    protected void auction(IGameStateDTO gameStateDTO){
        Player[] players = gameStateDTO.getPlayerController().getPlayers();
        //Get the player after the current player
        int index = (ArrayUtils.indexOf(players,gameStateDTO.getActivePlayer())+ 1) % players.length;
        //Previous player. One player have to buy the property
        int k= -1;
        int bid = 0;
        do{
            if(players[index].getBalance()>bid && gameStateDTO.getGuiController().getUserLeftButtonPressed(players[index].getIdentifier() + " do you want to bid?", "yes", "no")){
                bid = gameStateDTO.getGuiController().getBid("Hvor meget vil du byde? Sidste bud var " + bid, bid + 1, players[index].getBalance());
                k=index;
            }
            index = (index + 1) % players.length;
        }while(k!=index);

        players[index].setBalance(-bid);
        this.owner = players[index];
    }
}
