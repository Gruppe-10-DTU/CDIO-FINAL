package models.fields;

import controllers.GUIControllerStub;
import controllers.GameController;
import controllers.PlayerController;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    private GameStateDTO gameStateDTO;
    private PlayerController pc;
    private GUIControllerStub gui;
    @BeforeEach
    void setUp() {
        gameStateDTO = new GameStateDTO(new GUIControllerStub());
        pc = new PlayerController();
        pc.addPlayer(0,"car","vinder", 1);
        pc.addPlayer(1,"car","taber", 1);
        gui = new GUIControllerStub();
        gameStateDTO.setGuiController(gui);
    }

    @Test
    void auction() {
        Property property = new Street();
        pc.getPlayerById(1).setBalance(-29996);
        pc.getPlayerById(0).setBalance(-29990);
        gameStateDTO.setPlayerController(pc);
        gui.setButtonClicked(true);
        property.auction(gameStateDTO);
        assertEquals(5,pc.getPlayerById(0).getBalance());
        assertEquals(property.getOwner(), pc.getPlayerById(0));
    }
}