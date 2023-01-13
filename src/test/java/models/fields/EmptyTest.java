package models.fields;

import controllers.GUIControllerStub;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.Test;


class EmptyTest {
    @Test
    void testEmptyFieldNoChange() {
        GameStateDTO gameStateDTO = new GameStateDTO(new GUIControllerStub());
        new Empty().fieldEffect(gameStateDTO);
    }
}