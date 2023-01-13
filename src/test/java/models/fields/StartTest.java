package models.fields;

import controllers.GUIControllerStub;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.Test;
import ui.GUIController;

import static org.junit.jupiter.api.Assertions.*;

class StartTest {

    @Test
    void fieldEffectNoChance() {
        GameStateDTO gameStateDTO = new GameStateDTO(new GUIControllerStub());
        new Start().fieldEffect(gameStateDTO);
    }
}