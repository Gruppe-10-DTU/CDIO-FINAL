package models.fields;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreetTest {
    private static GameStateDTO gameStateDTO;
    private static FieldController fieldController;
    private static GUIControllerStub guiController;
    private static PlayerController playerController;

    @BeforeAll
    static void beforeAll() {
        Language language = new Language();
        fieldController = new FieldController(language);
        guiController = new GUIControllerStub();
        playerController = new PlayerController();
        playerController.addPlayer(0, "car", "test1",1);
        playerController.addPlayer(1, "car", "test2",1);

        gameStateDTO = new GameStateDTO(guiController);
        gameStateDTO.setActivePlayer(playerController.getPlayerById(0));
        gameStateDTO.setPlayerController(playerController);
    }

    @Test
    void fieldEffectNoOwner() {
        guiController.setButtonClicked(true);
        Street street = (Street) fieldController.getField(1);

        street.fieldEffect(gameStateDTO);

        assertEquals(street.getOwner(), gameStateDTO.getActivePlayer());
        assertEquals(30000-street.getPrice(), gameStateDTO.getActivePlayer().getBalance());
    }

    @Test
    void steetEffectGetRent() {
        Street street = (Street) fieldController.getField(1);
        street.setOwner(playerController.getPlayerById(1));
        street.fieldEffect(gameStateDTO);

        assertNotEquals(street.getOwner().getIdentifier(), gameStateDTO.getActivePlayer().getIdentifier());
        assertEquals(30000-street.getRent()[street.getHouseAmount()], gameStateDTO.getActivePlayer().getBalance());
        assertEquals(30000+street.getRent()[street.getHouseAmount()], street.getOwner().getBalance());

    }

    @Test
    void streetEffectKickPlayer() {
        Street street = (Street) fieldController.getField(1);
        playerController.getPlayerById(1).setBalance(50);
        street.setOwner(playerController.getPlayerById(1));
        playerController.getPlayerById(0).setBalance(-29950);
        street.fieldEffect(gameStateDTO);

        assertEquals(0, playerController.getPlayerById(0).getBalance());

        playerController.getPlayerById(0).setBalance(49);
        street.fieldEffect(gameStateDTO);

        assertEquals(1, playerController.getPlayers().length);
        assertNull(playerController.getPlayerById(0));

    }
}