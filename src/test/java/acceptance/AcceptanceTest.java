package acceptance;

import controllers.GameController;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ui.GUIController;

@Disabled("Only used for acceptance test")
public class AcceptanceTest {
    private GameController gameController;
    private GameStateDTO gameState;
    @BeforeEach
    public void beforeAll(){
        GUIController guiController = new GUIController();
    }
    @Test
    public void AK5_6(){
        System.out.println("Test");
    }
}