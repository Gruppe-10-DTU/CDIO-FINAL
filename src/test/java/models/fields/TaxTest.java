package models.fields;

import controllers.FieldController;
import controllers.FieldControllerStub;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Player;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxTest {

    private Tax tax;
    private GameStateDTO gameState;

    @BeforeEach
    void beforeEach(){
        gameState = new GameStateDTO(new GUIControllerStub());
        gameState.setActivePlayer(new Player(0, "test"));
        gameState.setFieldController(new FieldControllerStub());
        PlayerController pc = new PlayerController();
        pc.addPlayer(0, "car", "test",0);
        gameState.setPlayerController(pc);
        tax = new Tax("Tax", 0,1000, 0);
    }

    @Test
    public void testRemoveStaticAmount(){
        tax.fieldEffect(gameState);

        assertNotEquals(30000, gameState.getActivePlayer().getBalance());
        assertEquals(29000, gameState.getActivePlayer().getBalance());
    }

    @Test
    public void removeStaticAmountFromChoice(){
        tax.setPriceProcent(10);
        ((GUIControllerStub) gameState.getGuiController()).setButtonClicked(true);
        tax.fieldEffect(gameState);

        assertNotEquals(30000, gameState.getActivePlayer().getBalance());
        assertEquals(29000, gameState.getActivePlayer().getBalance());
    }

    @Test
    public void removeDynamicAmountFromChoice(){
        tax.setPriceProcent(10);
        ((GUIControllerStub) gameState.getGuiController()).setButtonClicked(false);
        tax.fieldEffect(gameState);

        assertNotEquals(30000, gameState.getActivePlayer().getBalance());
        assertEquals(27000, gameState.getActivePlayer().getBalance());
    }

}