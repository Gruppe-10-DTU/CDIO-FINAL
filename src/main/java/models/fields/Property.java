package models.fields;

import models.Player;
import models.dto.GameStateDTO;
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

    protected void auction(GameStateDTO gameStateDTO){
        Player[] players = gameStateDTO.getPlayerController().getPlayers();
        int index = (ArrayUtils.indexOf(players,gameStateDTO.getActivePlayer())+ 1) % players.length;
        int k= index;
        int bid = 0;
        {
            if(gameStateDTO.getGuiController().getUserLeftButtonPressed(players[index].getIdentifier() + " do you want to bid?", "yes", "no")){
                bid = gameStateDTO.getGuiController().getBid("Hvor meget vil du byde? Sidste bud var " + bid, bid, players[index].getBalance());
                k=index;
            }
            index = (index + 1) % players.length;
        }while(k!=index);
        players[index].setBalance(-this.price);
        this.owner = players[index];

    }
}
