package models.fields;

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
    public void testRemoveStaticAmountDead(){
        gameState.getActivePlayer().setBalance(-30000);

        tax.fieldEffect(gameState);
        assertEquals(0, gameState.getActivePlayer().getBalance());
        assertNull(gameState.getPlayerController().getPlayerById(0));
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
    public void removeDynamicAmountFromChoiceNoHouse(){
        tax.setPriceProcent(10);
        ((GUIControllerStub) gameState.getGuiController()).setButtonClicked(false);
        ((FieldControllerStub)gameState.getFieldController()).setPropertyValue(0);
        tax.fieldEffect(gameState);

        assertNotEquals(30000, gameState.getActivePlayer().getBalance());
        assertEquals(30000-30000*0.10, gameState.getActivePlayer().getBalance());
    }
    @Test
    public void removeDynamicAmount3000PropertyValue(){
        tax.setPriceProcent(10);
        ((GUIControllerStub) gameState.getGuiController()).setButtonClicked(false);
        ((FieldControllerStub)gameState.getFieldController()).setPropertyValue(3000);
        tax.fieldEffect(gameState);

        assertNotEquals(30000, gameState.getActivePlayer().getBalance());
        assertEquals(30000-33000*0.10, gameState.getActivePlayer().getBalance());
    }
    @Test
    public void removeDynamicChoiceAmountDead(){
        tax.setPriceProcent(100);
        ((GUIControllerStub) gameState.getGuiController()).setButtonClicked(false);
        ((FieldControllerStub)gameState.getFieldController()).setPropertyValue(1);
        tax.fieldEffect(gameState);

        assertEquals(30000, gameState.getActivePlayer().getBalance());
        assertNull(gameState.getPlayerController().getPlayerById(0));
    }
}