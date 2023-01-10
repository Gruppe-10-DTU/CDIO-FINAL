package models.fields;

import controllers.GUIControllerStub;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @BeforeEach
    void setUp() {
        GameStateDTO gameStateDTO = new GameStateDTO(new GUIControllerStub());
    }

    @Test
    void auction() {
    }
}