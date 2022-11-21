package controllers;

import models.Language;
import models.Player;
import models.chanceCards.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameControllerTest {


    GameController gameController;
    DiceHolder diceHolder;
    @Test
    void testBalance() {
        assertEquals(1, 1);
    }

    @Test
    void testGetOutOfJail(){
        Player testPlayer = new Player(0, "Test");
        gameController.TakeTurn(testPlayer);
    }

    @BeforeEach
    void setUp() {
        Language language = new Language();
        FieldController fieldController = new FieldController(language);
        GUIControllerStub gui = new GUIControllerStub(fieldController.getFieldList());
        PlayerController pc = new PlayerController(gui.playerAmount("test"));
        Deck deck = new Deck(language);
        diceHolder = new cheatDiceHolder();
        for (int i = 0; i < gui.playerAmount("test"); i++) {
            pc.addPlayer(i, gui.selectCharacter("test", "test"), gui.getName("test"));
        }

        gameController = new GameController(language, pc, fieldController, gui, deck, diceHolder);
    }
}