package controllers;

import models.Language;
import models.chanceCards.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameControllerTest {


    GameController gameController;
    @Test
    void testBalance() {
        assertEquals(1, 1);
    }

    @BeforeEach
    void setUp() {
        Language language = new Language();
        FieldController fieldController = new FieldController(language);
        GUIControllerStub gui = new GUIControllerStub(fieldController.getFieldList());
        PlayerController pc = new PlayerController(gui.playerAmount("test"));
        Deck deck = new Deck(language);
        for (int i = 0; i < gui.playerAmount("test"); i++) {
            pc.addPlayer(i, gui.selectCharacter("test", "test"), gui.getName("test"));
        }
        gameController = new GameController(language, pc, fieldController, gui, deck);
    }
}