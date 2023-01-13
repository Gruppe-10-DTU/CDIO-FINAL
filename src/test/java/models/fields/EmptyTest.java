package models.fields;

import controllers.GUIControllerStub;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyTest {
    @Test
    void testEmptyFieldNoChange() {
        GameStateDTO gameStateDTO = new GameStateDTO(new GUIControllerStub());
        new Empty().fieldEffect(gameStateDTO);
    }
}