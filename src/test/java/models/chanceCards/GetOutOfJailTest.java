package models.chanceCards;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Player;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GetOutOfJailTest {
    GameStateDTO gameState;
    GetOutOfJail card;

    @BeforeEach
    void setUp() {
        Player player1 = new Player(0,"Player1");
        ArrayList<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(new Player(1,"player2"));
        gameState = new GameStateDTO(player1,otherPlayers);
        gameState.setPlayerController(new PlayerController());
        gameState.setGuiController(new GUIControllerStub());
        gameState.setFieldController(new FieldController());
        card = new GetOutOfJail("card1", "");
    }

    @Test
    @DisplayName("test the player gets the card")
    void chanceEffect() {
        card.chanceEffect(gameState);
        Player activePlayer = gameState.getActivePlayer();
        assertTrue(activePlayer.hasGetOutOfJail());
    }
}