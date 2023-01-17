package models;

import controllers.FieldController;
import controllers.GUIControllerStub;
import junit.framework.TestCase;
import models.chanceCards.Deck;
import models.chanceCards.GetOutOfJail;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PlayerTest extends TestCase {
    Player player;

    @BeforeEach
    void setup(){
        player = new Player(0, "name");
    }
@Test
    public void testSetBalanceValidate() {

        assertTrue(player.setBalance(-10));
        assertEquals(29990, player.getBalance());
    }
@Test
    public void testSetBalanceNotNegative() {

        assertTrue(player.setBalance(-30000));
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
        GameStateDTO gameState = new GameStateDTO(player, new ArrayList<>(1));
        gameState.setGuiController(new GUIControllerStub());
        gameState.setFieldController(new FieldController());
        gameState.setChanceCardDeck(new Deck());
        GetOutOfJail card = new GetOutOfJail("GET_OUT_OF_JAIL_1", "Get Out of Jail card");
        player.addGetOutOfJail(card);
        gameState.getFieldController().jailPlayer(player);
        assertTrue(player.hasGetOutOfJail());
        assertEquals(card, player.useGetOutOfJail());
        assertFalse(player.hasGetOutOfJail());
    }
}