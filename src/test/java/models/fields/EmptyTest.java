package models.fields;

import controllers.GUIControllerStub;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyTest {
    @Test
    void testEmptyFieldNoChange() {
        GameStateDTO gameStateDTO = new GameStateDTO(new GUIControllerStub());
        Empty empty = new Empty();

        assertEquals(gameStateDTO, empty.fieldEffect(gameStateDTO));
    }
}