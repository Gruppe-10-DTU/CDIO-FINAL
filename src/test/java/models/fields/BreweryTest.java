package models.fields;

import controllers.*;
import models.Language;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreweryTest {

    private static GameStateDTO gameStateDTO;
    private static FieldController fieldController;
    private static GUIControllerStub guiController;
    private static PlayerController playerController;

    private static CheatDiceHolder cheatDiceHolder;

    @BeforeEach
    void beforeEach() {
        Language language = new Language();
        fieldController = new FieldController(language);
        guiController = new GUIControllerStub();
        playerController = new PlayerController();
        playerController.addPlayer(0, "car", "test1",1);
        playerController.addPlayer(1, "car", "test2",1);
        cheatDiceHolder = new CheatDiceHolder();
        cheatDiceHolder.setRolls(4, 4);

        gameStateDTO = new GameStateDTO(guiController);
        gameStateDTO.setActivePlayer(playerController.getPlayerById(0));
        gameStateDTO.setPlayerController(playerController);
        gameStateDTO.setDiceHolder(cheatDiceHolder);
        gameStateDTO.setFieldController(fieldController);
    }

    @Test
    void fieldEffectNoOwner() {
        guiController.setButtonClicked(true);
        Brewery brewery = (Brewery) fieldController.getField(12);

        brewery.fieldEffect(gameStateDTO);

        assertEquals(brewery.getOwner(), gameStateDTO.getActivePlayer());
        assertEquals(30000-brewery.getPrice(), gameStateDTO.getActivePlayer().getBalance());
    }

    @Test
    void breweryEffectGetRent() {
        Brewery brewery = (Brewery) fieldController.getField(12);
        brewery.setOwner(playerController.getPlayerById(1));
        brewery.fieldEffect(gameStateDTO);

        assertNotEquals(brewery.getOwner().getIdentifier(), gameStateDTO.getActivePlayer().getIdentifier());
        assertEquals(30000-brewery.getRent0()*cheatDiceHolder.sum(), gameStateDTO.getActivePlayer().getBalance());
        assertEquals(30000+brewery.getRent0()*cheatDiceHolder.sum(), brewery.getOwner().getBalance());
    }

    @Test
    void streetEffectKickPlayer() {
        Brewery brewery = (Brewery) fieldController.getField(12);
        playerController.getPlayerById(1).setBalance(800);
        brewery.setOwner(playerController.getPlayerById(1));
        playerController.getPlayerById(0).setBalance(-29200);
        brewery.fieldEffect(gameStateDTO);

        assertEquals(0, playerController.getPlayerById(0).getBalance());

        playerController.getPlayerById(0).setBalance(799);
        brewery.fieldEffect(gameStateDTO);

        assertEquals(1, playerController.getPlayers().length);
        assertNull(playerController.getPlayerById(0));

    }

    @Test
    void breweryEffectGetRent2Owned() {
        Brewery brewery1 = (Brewery) fieldController.getField(12);
        brewery1.setOwner(playerController.getPlayerById(1));
        Brewery brewery2 = (Brewery) fieldController.getField(28);
        brewery2.setOwner(playerController.getPlayerById(1));

        brewery1.fieldEffect(gameStateDTO);

        assertNotEquals(brewery1.getOwner().getIdentifier(), gameStateDTO.getActivePlayer().getIdentifier());
        assertEquals(30000-brewery1.getRent1()*cheatDiceHolder.sum(), gameStateDTO.getActivePlayer().getBalance());
        assertEquals(30000+brewery1.getRent1()*cheatDiceHolder.sum(), brewery1.getOwner().getBalance());
    }

}