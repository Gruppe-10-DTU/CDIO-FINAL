package models.fields;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreetTest {
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
        Street street = (Street) fieldController.getField(1);

        street.fieldEffect(gameStateDTO);

        assertEquals(street.getOwner(), gameStateDTO.getActivePlayer());
        assertEquals(30000-street.getPrice(), gameStateDTO.getActivePlayer().getBalance());
    }

    @Test
    void streetEffectGetRent() {
        Street street = (Street) fieldController.getField(1);
        street.setOwner(playerController.getPlayerById(1));
        street.fieldEffect(gameStateDTO);

        assertNotEquals(street.getOwner().getIdentifier(), gameStateDTO.getActivePlayer().getIdentifier());
        assertEquals(30000-street.getRent()[street.getHouseAmount()], gameStateDTO.getActivePlayer().getBalance());
        assertEquals(30000+street.getRent()[street.getHouseAmount()], street.getOwner().getBalance());
    }

    @Test
    void streetEffectOwnerInJail() {
        Street street = (Street) fieldController.getField(1);
        street.setOwner(playerController.getPlayerById(1));
        gameStateDTO.getFieldController().jailPlayer(playerController.getPlayerById(1));

        assertTrue(fieldController.isJailed(playerController.getPlayerById(1)));

        street.fieldEffect(gameStateDTO);

        assertEquals(30000, playerController.getPlayerById(0).getBalance());
    }

    @Test
    void doubleRent2Properties() {
        Street blueStreet1 = (Street) fieldController.getField(1);
        blueStreet1.setOwner(playerController.getPlayerById(1));
        Street blueStreet2 = (Street) fieldController.getField(3);
        blueStreet2.setOwner(playerController.getPlayerById(1));

        blueStreet1.fieldEffect(gameStateDTO);

        assertEquals(30000-blueStreet1.getRent()[0]*2, gameStateDTO.getActivePlayer().getBalance());
        assertEquals(30000+blueStreet1.getRent()[0]*2, blueStreet1.getOwner().getBalance());

    }

    @Test
    void doubleRent3Properties() {
        Street pinkStreet1 = (Street) fieldController.getField(6);
        pinkStreet1.setOwner(playerController.getPlayerById(1));
        Street pinkStreet2 = (Street) fieldController.getField(8);
        pinkStreet2.setOwner(playerController.getPlayerById(1));
        Street pinkStreet3 = (Street) fieldController.getField(9);
        pinkStreet3.setOwner(playerController.getPlayerById(1));

        pinkStreet3.fieldEffect(gameStateDTO);

        assertEquals(30000-pinkStreet3.getRent()[0]*2, gameStateDTO.getActivePlayer().getBalance());
        assertEquals(30000+pinkStreet3.getRent()[0]*2, pinkStreet3.getOwner().getBalance());

    }
}