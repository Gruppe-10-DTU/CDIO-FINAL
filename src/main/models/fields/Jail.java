package game.models.fields;

import game.models.Player;

import java.util.ArrayList;

public class Jail extends Field {

    private ArrayList<Player> inJail;

    public ArrayList<Player> getInJail() {
        return inJail;
    }

    public boolean isInJail (Player player) {
        if (inJail.contains(player)) {
            return true;
        } else {
            return false;
        }
    }

    public void setInJailAdd(Player player) {
        inJail.add(player);
    }

    public void setInJailRemove(Player player) {
        int index = inJail.indexOf(player);
        inJail.remove(index);
    }
}
