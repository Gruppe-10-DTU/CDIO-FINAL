package models.chanceCards;

import models.dto.GameStateDTO;
import org.apache.commons.lang.NotImplementedException;

public class Grant extends ChanceCard{

    private final int BONUS;
    private final int NET_WORTH;
    public Grant(String NAME, String Description, int MaximumPlayerWorth, int AwardsBonus) {
        super(NAME, Description);
        this.BONUS = AwardsBonus;
        this.NET_WORTH = MaximumPlayerWorth;
    }

    public int getBONUS() {
        return BONUS;
    }

    public int getNET_WORTH() {
        return NET_WORTH;
    }

    @Override
    public GameStateDTO chanceEffect(GameStateDTO gameState){
        throw new NotImplementedException();
    }
}
