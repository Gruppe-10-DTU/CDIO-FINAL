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
    void auctionTwoPeople() {
        Property property = new Street();
        pc.getPlayerById(1).setBalance(-29996);
        pc.getPlayerById(0).setBalance(-29990);
        gameStateDTO.setPlayerController(pc);
        gui.setButtonClicked(true);
        property.auction(gameStateDTO);
        assertEquals(5,pc.getPlayerById(0).getBalance());
        assertEquals(property.getOwner(), pc.getPlayerById(0));
    }
    @Test
    void auctionOnePersonCanAfford() {
        Property property = new Street();
        pc.getPlayerById(0).setBalance(-30000);
        gameStateDTO.setPlayerController(pc);
        gui.setButtonClicked(true);
        property.auction(gameStateDTO);
        assertEquals(29999,pc.getPlayerById(1).getBalance());
        assertEquals(property.getOwner(), pc.getPlayerById(1));
    }

    @Test
    void auctionCorrectOrder() {
        Property property = new Street();
        gameStateDTO.setActivePlayer(pc.getPlayerById(0));
        pc.getPlayerById(0).setBalance(-29999);
        pc.getPlayerById(1).setBalance(-29999);
        gameStateDTO.setPlayerController(pc);
        gui.setButtonClicked(true);
        property.auction(gameStateDTO);
        assertEquals(0,pc.getPlayerById(1).getBalance());
        assertEquals(property.getOwner(), pc.getPlayerById(1));
    }
}