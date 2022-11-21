package controllers;

import models.Language;
import models.Player;
import models.chanceCards.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GameControllerTest {
    GameController gameController;
    cheatDiceHolder diceHolder;
    PlayerController pc;
    FieldController fieldController;
    GUIControllerStub gui;
    Language language;
    Deck deck;
    @Test
    void testGetOutOfJail(){
        Player testPlayer = new Player(0, "Test");
        //Set player in jail
        testPlayer.setLocation(6);
        //Make sure the next roll is 0
        diceHolder.setNextRoll(0);
        fieldController.jailPlayer(testPlayer);
        gameController.TakeTurn(testPlayer);
        assertNotEquals(20, testPlayer.getBalance());
        assertEquals(18, testPlayer.getBalance());
    }

    @BeforeEach
    void setUp() {
        language = new Language();
        fieldController = new FieldController(language);
        gui = new GUIControllerStub(fieldController.getFieldList());
        pc = new PlayerController(gui.playerAmount("test"));
        deck = new Deck(language);
        diceHolder = new cheatDiceHolder();
        for (int i = 0; i < gui.playerAmount("test"); i++) {
            pc.addPlayer(i, gui.selectCharacter("test", "test"), gui.getName("test"));
        }

        gameController = new GameController(language, pc, fieldController, gui, deck, diceHolder);
    }
    @Test
    void testEqualBalance() {
        fieldController.setOwner(pc.getPlayers()[0],1);
        assertEquals(pc.getPlayers()[0].getIdentifier(), gameController.checkAllBalance());
    }
    @Test
    void testZeroBalance() {
        pc.payMoney(pc.getPlayers()[1],1000);
        pc.payMoney(pc.getPlayers()[2],1000);
//        assertEquals(pc.getPlayers()[0].getIdentifier(), gameController.checkAllBalance());
    }
}