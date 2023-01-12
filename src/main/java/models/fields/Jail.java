package models.fields;

import controllers.DiceHolder;
import controllers.FieldController;
import models.Player;
import models.dto.IGameStateDTO;
import ui.GUIController;

import java.util.ArrayList;

public class Jail extends Field {

    private ArrayList<Player> inJail = new ArrayList<>();
    private final int getOutOfJailPrice;

    public Jail(int priceToGetOutOfJail){
        this.getOutOfJailPrice = priceToGetOutOfJail;
    }

    public ArrayList<Player> getInJail() {
        return inJail;
    }

    /**
     * @param player
     * @return True: if player is in the jail array / False if player is not in the jail array
     */
    public boolean isInJail (Player player) {
        return inJail.contains(player);
    }
    /**
     *
     * @param player
     * Add a player to the jail array
     */
    public void setInJailAdd(Player player) {
        inJail.add(player);
    }

    /**
     *
     * @param player
     * Remove a player from the jail array
     */
    public void setInJailRemove(Player player) {
        int index = inJail.indexOf(player);
        inJail.remove(index);
    }

    @Override
    public void fieldEffect(IGameStateDTO gameState){
        if(!(this.isInJail(gameState.getActivePlayer()))) return;

        Player player = gameState.getActivePlayer();
        GUIController io = gameState.getGuiController();

        String choice;

        if(player.hasGetOutOfJail() && (player.getBalance() + getOutOfJailPrice) > 0) {
            choice = io.getOutOfJailOptions(true, true);
        }else if(player.getBalance() >= getOutOfJailPrice * -1){
            choice = io.getOutOfJailOptions(true,false);
        } else if (player.hasGetOutOfJail()) {
            choice = io.getOutOfJailOptions(false, true);
        } else {
            choice = io.getOutOfJailOptions(false, false);
        }

        FieldController fieldController = gameState.getFieldController();

        switch (choice) {
            case "pay":
                player.setBalance(getOutOfJailPrice);
                player.setRoundsInJail(0);
                this.setInJailRemove(player);

                /* OUTPUT MESSAGE To USER */

                break;
            case "roll":
                for (int i = 0; i < 3; i++) {
                    DiceHolder diceHolder = gameState.getDiceHolder();
                    diceHolder.roll();
                    int[] jailRoll = diceHolder.getRolls();
                    io.displayDice(jailRoll);
                    if (jailRoll[0] == jailRoll[1]){
                        player.setRoundsInJail(0);
                        this.setInJailRemove(player);

                        /* OUTPUT MESSAGE To USER */
                        gameState.getPlayerController().playerMove(player, diceHolder.sum());
                        gameState.getGuiController().movePlayer(player);
                        gameState.getFieldController().landOnField(gameState);

                        break;
                    }else if (jailRoll[0] != jailRoll[1] && i != 2) {
                        io.getOutOfJailRollAgain();
                    }
                }
                player.stayInJail();
                break;
            case "card":
                player.useGetOutOfJail(gameState);

                /* OUTPUT MESSAGE To USER */

                break;
        }
        if(player.getRoundsInJail() >= 3){
            player.setBalance(getOutOfJailPrice);
            player.setRoundsInJail(0);
            this.setInJailRemove(player);

            /* OUTPUT MESSAGE To USER */
        }
    }
}
