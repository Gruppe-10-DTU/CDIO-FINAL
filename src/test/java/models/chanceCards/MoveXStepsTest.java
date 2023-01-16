package models.chanceCards;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MoveXStepsTest {
    GameStateDTO gameState;
    MoveXSteps card1;
    MoveXSteps card2;
    MoveXSteps card3;

    @BeforeEach
    void setUp() {
        Player player1 = new Player(0,"Player1");
        ArrayList<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(new Player(1,"player2"));
        gameState = new GameStateDTO(player1,otherPlayers);
        gameState.setPlayerController(new PlayerController());
        gameState.setGuiController(new GUIControllerStub());
        gameState.setFieldController(new FieldController(new Language()));
        gameState.setChanceCardDeck(new Deck(new Language()));
        card1 = new MoveXSteps("card1", "",3);
        card2 = new MoveXSteps("card2", "",-3);
        card3 = new MoveXSteps("card3", "",-3);

    }

    @Test
    @DisplayName("player is moved the correct amount")
    void moveXStepsChanceEffect() {
        gameState.getActivePlayer().setLocation(2);
        card1.chanceEffect(gameState);
        assertEquals(5,gameState.getActivePlayer().getLocation());

        gameState.getActivePlayer().setLocation(17);
        card2.chanceEffect(gameState);
        assertEquals(14,gameState.getActivePlayer().getLocation());

        gameState.getActivePlayer().setLocation(2);
        card3.chanceEffect(gameState);
        assertEquals(39,gameState.getActivePlayer().getLocation());
    }
    @Test
    @DisplayName("game in reverse mode")
    void chanceEffectReverse(){
        gameState.setReverse(true);
        gameState.getActivePlayer().setLocation(2);
        card1.chanceEffect(gameState);
        assertEquals(39,gameState.getActivePlayer().getLocation());

        gameState.getActivePlayer().setLocation(17);
        card2.chanceEffect(gameState);
        assertEquals(20,gameState.getActivePlayer().getLocation());

        gameState.getActivePlayer().setLocation(33);
        card3.chanceEffect(gameState);
        assertEquals(36,gameState.getActivePlayer().getLocation());
    }
}