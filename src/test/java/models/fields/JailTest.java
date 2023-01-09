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

class JailTest {
    GameStateDTO gameState;
    PlayerController playerController;
    FieldController fieldController;
    Jail jail;

    @BeforeEach
    void setUp() {
        playerController = new PlayerController();
        playerController.addPlayer(0,"car","player1",0);
        playerController.addPlayer(1,"car", "player2", 1);
        fieldController = new FieldController(new Language());
        jail = (Jail) fieldController.getField(10);

    }

    @Test
    @DisplayName("Check that there is a list of jailed players")
    void getInJail() {
        assertNotNull(jail.getInJail());
    }

    @Test
    @DisplayName("Check if player is jailed")
    void isInJail() {
        assertFalse(jail.isInJail(playerController.getPlayerById(0)));
        assertFalse(jail.isInJail(playerController.getPlayerById(1)));
    }

    @Test
    @DisplayName("Set a player in jail")
    void setInJailAdd() {
        jail.setInJailAdd(playerController.getPlayerById(0));
        assertTrue(jail.getInJail().size() > 0);
    }

    @Test
    @DisplayName("Remove a player from jail")
    void setInJailRemove() {

        jail.setInJailAdd(playerController.getPlayerById(0));
        assertTrue(jail.isInJail(playerController.getPlayerById(0)));
        jail.setInJailRemove(playerController.getPlayerById(0));
        assertEquals(0, jail.getInJail().size());
    }

    @Test
    @DisplayName("Check that the player can be released if they are jailed")
    void fieldEffect() {
        PlayerController playerController = new PlayerController();
        playerController.addPlayer(0,"car","player1",0);
        playerController.addPlayer(1,"car", "player2", 1);
        FieldController fieldController = new FieldController(new Language());
        gameState = new GameStateDTO(playerController.getPlayerById(0), playerController.otherPlayers(1));
        gameState.setPlayerController(playerController);
        GUIControllerStub guiControllerStub = new GUIControllerStub();
        gameState.setFieldController(fieldController);
        gameState.setGuiController(guiControllerStub);


    }
}