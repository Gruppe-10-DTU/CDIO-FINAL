package models;

import junit.framework.TestCase;
import models.chanceCards.GetOutOfJail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest extends TestCase {
    Player player;

    @BeforeEach
    void setup(){
        player = new Player(0, "name");
    }
@Test
    public void testSetBalanceValidate() {

        assertTrue(player.setBalance(-10));
        assertEquals(10, player.getBalance());
    }
@Test
    public void testSetBalanceNotNegative() {

        assertTrue(player.setBalance(-20));
        assertFalse(player.setBalance(-1));
        assertEquals(0, player.getBalance());
    }

    @Test
    @DisplayName("Player can receive get out of jail card")
    public void addGetOutOfJail(){
        GetOutOfJail card = new GetOutOfJail("GET_OUT_OF_JAIL_1", "Get Out of Jail card");
        assertFalse(player.hasGetOutOfJail());
        player.addGetOutOfJail(card);
        assertTrue(player.hasGetOutOfJail());
    }
    @Test
    @DisplayName("Player can use get out of jail card")
    public void useGetOutOfJail(){
        GetOutOfJail card = new GetOutOfJail("GET_OUT_OF_JAIL_1", "Get Out of Jail card");
        player.addGetOutOfJail(card);
        assertTrue(player.hasGetOutOfJail());
        player.useGetOutOfJail();
        assertFalse(player.hasGetOutOfJail());
    }
}