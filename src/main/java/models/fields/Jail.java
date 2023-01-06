package models.fields;

import controllers.DiceHolder;
import controllers.FieldController;
import controllers.StartValues;
import models.Player;
import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

import java.util.ArrayList;

public class Jail extends Field {

    private ArrayList<Player> inJail = new ArrayList<>();

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
        FieldController fieldController= GameStateDTO.getFieldController();
        DiceHolder diceHolder = GameStateDTO.getDiceHolder();
        String message = language.getLanguageValue("getOutOfJail");
        String pay = language.getLanguageValue("payOutOfJail");
        String roll = language.getLanguageValue("rollOutOfJail");
        String card = language.getLanguageValue("cardOutOfJail");
        String choice;

        if(player.getGetOutOfJail() != null) {
            choice = io.getOutOfJailOptions(message, new String[]{pay,roll,card});
        }else{
            choice = io.getOutOfJailOptions(message, new String[]{pay,roll});
        }

        switch (choice) {
            case "pay":
                player.setBalance(StartValues.getInstance().getValue("getOutOfJailPrice"));
                player.setRoundsInJail(0);
                fieldController.freePlayer(player);
                io.displayMsgNoBtn(language.getLanguageValue("payOutOfJail"));
                break;
            case "roll":
                for (int i = 0; i < 3; i++) {
                    diceHolder.roll();
                    int[] jailRoll = diceHolder.getRolls();
                    io.displayDice(jailRoll);
                    if (jailRoll[0] == jailRoll[1]){
                        player.setRoundsInJail(0);
                        fieldController.freePlayer(player);
                        io.displayMsgNoBtn(language.getLanguageValue("rollOutOfJail"));
                        break;
                    }
                }
                player.setRoundsInJail(player.getRoundsInJail() + 1);
                break;
            case "card":
                player.setRoundsInJail(0);
                player.setGetOutOfJail(null);
                fieldController.freePlayer(player);
                io.displayMsgNoBtn(language.getLanguageValue("cardOutOfJail"));
                break;
        }
        if(player.getRoundsInJail() >= 3){
            player.setBalance(StartValues.getInstance().getValue("getOutOfJailPrice"));
            player.setRoundsInJail(0);
            fieldController.freePlayer(player);
            io.displayMsgNoBtn(language.getLanguageValue("payOutOfJail"));
        }
    }
}
