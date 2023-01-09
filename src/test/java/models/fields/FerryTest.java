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

class FerryTest {

    private static GameStateDTO gameStateDTO;
    private static FieldController fieldController;
    private static GUIControllerStub guiController;
    private static PlayerController playerController;

    @BeforeEach
    void beforeAll() {
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
        Ferry ferry = (Ferry) fieldController.getField(5);

        ferry.fieldEffect(gameStateDTO);

        assertEquals(ferry.getOwner(), gameStateDTO.getActivePlayer());
        assertEquals(30000-ferry.getPrice(), gameStateDTO.getActivePlayer().getBalance());
    }


}