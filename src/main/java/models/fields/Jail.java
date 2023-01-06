package models.fields;

import controllers.DiceHolder;
import controllers.FieldController;
import models.Player;
import models.dto.GameStateDTO;
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
    public GameStateDTO fieldEffect(GameStateDTO gameState){
        Player player = gameState.getActivePlayer();
        GUIController io = gameState.getGuiController();

        String choice;

        if(player.getGetOutOfJail() != null && (player.getBalance() + getOutOfJailPrice) > 0) {
            choice = io.getOutOfJailOptions(true, true);
        }else if(player.getBalance() + getOutOfJailPrice > 0){
            choice = io.getOutOfJailOptions(true,false);
        } else if (player.getGetOutOfJail() != null) {
            choice = io.getOutOfJailOptions(false, true);
        } else {
            choice = io.getOutOfJailOptions(false, false);
        }

        FieldController fieldController = gameState.getFieldController();

        switch (choice) {
            case "pay":
                player.setBalance(getOutOfJailPrice);
                player.setRoundsInJail(0);
                fieldController.freePlayer(player);

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
                        fieldController.freePlayer(player);

                        /* OUTPUT MESSAGE To USER */

                        break;
                    }
                }
                player.setRoundsInJail(player.getRoundsInJail() + 1);
                break;
            case "card":
                player.setRoundsInJail(0);
                player.setGetOutOfJail(null);
                fieldController.freePlayer(player);

                /* OUTPUT MESSAGE To USER */

                break;
        }
        if(player.getRoundsInJail() >= 3){
            player.setBalance(getOutOfJailPrice);
            player.setRoundsInJail(0);
            fieldController.freePlayer(player);

            /* OUTPUT MESSAGE To USER */

        }
        return  gameState;
    }
}
