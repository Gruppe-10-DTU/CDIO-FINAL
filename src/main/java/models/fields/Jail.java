package models.fields;

import models.Player;

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
}
