package models.fields;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FerryTest {

    private static GameStateDTO gameStateDTO;
    private static FieldController fieldController;
    private static GUIControllerStub guiController;
    private static PlayerController playerController;

    @BeforeEach
    void beforeAll() {
        fieldController = new FieldController();
        guiController = new GUIControllerStub();
        playerController = new PlayerController();
        playerController.addPlayer(0, "car", "test1",1);
        playerController.addPlayer(1, "car", "test2",1);

        gameStateDTO = new GameStateDTO(guiController);
        gameStateDTO.setActivePlayer(playerController.getPlayerById(0));
        gameStateDTO.setPlayerController(playerController);
        gameStateDTO.setFieldController(fieldController);
    }



    @Test
    void fieldEffectNoOwner() {
        guiController.setButtonClicked(true);
        Ferry ferry = (Ferry) fieldController.getField(5);

        ferry.fieldEffect(gameStateDTO);

        assertEquals(ferry.getOwner(), gameStateDTO.getActivePlayer());
        assertEquals(30000-ferry.getPrice(), gameStateDTO.getActivePlayer().getBalance());
    }

    @Test
    void steetEffectGetRent() {
        Ferry ferry = (Ferry) fieldController.getField(5);
        ferry.setOwner(playerController.getPlayerById(1));
        ferry.fieldEffect(gameStateDTO);

        assertNotEquals(ferry.getOwner().getIdentifier(), gameStateDTO.getActivePlayer().getIdentifier());
        assertEquals(30000-ferry.getRent()[0], gameStateDTO.getActivePlayer().getBalance());
        assertEquals(30000+ferry.getRent()[0], ferry.getOwner().getBalance());

    }

    @Test
    void streetEffectKickPlayer() {
        Ferry ferry = (Ferry) fieldController.getField(5);
        playerController.getPlayerById(1).setBalance(50);
        ferry.setOwner(playerController.getPlayerById(1));
        playerController.getPlayerById(0).setBalance(-29500);
        ferry.fieldEffect(gameStateDTO);

        assertEquals(0, playerController.getPlayerById(0).getBalance());

        playerController.getPlayerById(0).setBalance(499);
        ferry.fieldEffect(gameStateDTO);

        assertEquals(1, playerController.getPlayers().length);
        assertNull(playerController.getPlayerById(0));

    }

    @Test
    void steetEffectGetRent2Owned() {
        Ferry ferry1 = (Ferry) fieldController.getField(5);
        ferry1.setOwner(playerController.getPlayerById(1));
        Ferry ferry2 = (Ferry) fieldController.getField(15);
        ferry2.setOwner(playerController.getPlayerById(1));
        ferry1.fieldEffect(gameStateDTO);

        assertNotEquals(ferry1.getOwner().getIdentifier(), gameStateDTO.getActivePlayer().getIdentifier());
        assertEquals(30000-ferry1.getRent()[1], gameStateDTO.getActivePlayer().getBalance());
        assertEquals(30000+ferry1.getRent()[1], ferry1.getOwner().getBalance());
    }

    @Test
    void steetEffectGetRent4Owned() {
        Ferry ferry1 = (Ferry) fieldController.getField(5);
        ferry1.setOwner(playerController.getPlayerById(1));
        Ferry ferry2 = (Ferry) fieldController.getField(15);
        ferry2.setOwner(playerController.getPlayerById(1));
        Ferry ferry3 = (Ferry) fieldController.getField(25);
        ferry3.setOwner(playerController.getPlayerById(1));
        Ferry ferry4 = (Ferry) fieldController.getField(35);
        ferry4.setOwner(playerController.getPlayerById(1));
        ferry4.fieldEffect(gameStateDTO);

        assertNotEquals(ferry4.getOwner().getIdentifier(), gameStateDTO.getActivePlayer().getIdentifier());
        assertEquals(30000-ferry4.getRent()[3], gameStateDTO.getActivePlayer().getBalance());
        assertEquals(30000+ferry4.getRent()[3], ferry4.getOwner().getBalance());
    }



}