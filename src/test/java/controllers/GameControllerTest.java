package controllers;

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
        gameController = new GameController();
        Class c = gameController.getClass();
        Field guiControllerField = null;
        try {
            guiControllerField = c.getDeclaredField("guiController");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        guiControllerField.setAccessible(true);
        GUIController guiController = new GUIController();
        try {
            guiControllerField.set(gameController, guiController);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        gameController.initialize();
    }
}