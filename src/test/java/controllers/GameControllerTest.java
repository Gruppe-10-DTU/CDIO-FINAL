package controllers;

import models.chanceCards.Deck;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    GameController gameController;
    CheatDiceHolder diceHolder;
    PlayerController pc;
    FieldController fieldController;
    GUIControllerStub gui;
    Deck deck;
    GameStateDTO gameState;

    @BeforeEach
    void setUp() {
        fieldController = new FieldController();
        gui = new GUIControllerStub(fieldController.getFieldList());
        gameState = new GameStateDTO(gui);
        diceHolder = new CheatDiceHolder(2);
        gameState.setDiceHolder(diceHolder);
        pc = new PlayerController();
        gameState.setPlayerController(pc);
        deck = new Deck();
        gameState.setChanceCardDeck(deck);
        gameState.setFieldController(fieldController);
        for (int i = 0; i < gui.playerAmount("test"); i++) {
            pc.addPlayer(i, gui.selectCharacter("test", "test"), gui.getName("test"),0);
        }

        gameController = new GameController(gameState, deck);
    }

    @Test
    void win() {
        for (int i = pc.getPlayers().length; i > 1 ; i--) {
            assertNotEquals(true,gameController.win());
            pc.removePlayer(i);
        }
        assertFalse(gameController.win());
    }

    /*
    @Test
    void testExtraTurn() {
        pc.getPlayerById(0).setLocation(0);
        diceHolder.setRolls(3,3);
        diceHolder.setIsEqualAmount(1);
        pc.getPlayerById(1).setBalance(-29999);
        ((Street)fieldController.getField(6)).setOwner(pc.getPlayerById(0));
        gameController.startGame();
        assertNotEquals(6, pc.getPlayerById(0).getLocation());
        assertEquals(12, pc.getPlayerById(0).getLocation());
    }


    @Test
    void testJailPlayer3Rolls() {
        pc.getPlayerById(0).setLocation(0);
        diceHolder.setRolls(3,3);
        diceHolder.setIsEqualAmount(3);
        pc.getPlayerById(1).setBalance(-29999);
        ((Street)fieldController.getField(6)).setOwner(pc.getPlayerById(0));
        gameController.startGame();
        assertTrue(fieldController.isJailed(pc.getPlayerById(0)));
        assertEquals(10, pc.getPlayerById(0).getLocation());
    }

     */
}
