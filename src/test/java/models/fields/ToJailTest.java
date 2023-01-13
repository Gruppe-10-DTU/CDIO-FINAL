package models.fields;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToJailTest {
    GameStateDTO gameState;

    @BeforeEach
    void setUp() {
        PlayerController playerController = new PlayerController();
        playerController.addPlayer(0,"car","player1",0);
        playerController.addPlayer(1,"car", "player2", 1);
        FieldController fieldController = new FieldController(new Language());
        gameState = new GameStateDTO(new GUIControllerStub());
        gameState.setPlayerController(playerController);
        gameState.setFieldController(fieldController);
        gameState.setActivePlayer(playerController.getPlayerById(0));
    }

    @Test
    @DisplayName("Check that the player is sent to jail")
    void fieldEffect() {
        Field field = gameState.getFieldController().getField(30);
        assertFalse(gameState.getFieldController().isJailed(gameState.getActivePlayer()));
        field.fieldEffect(gameState);
        assertTrue(gameState.getFieldController().isJailed(gameState.getActivePlayer()));
        assertEquals(10,gameState.getActivePlayer().getLocation());
    }
}