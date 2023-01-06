package acceptance;

import controllers.FieldController;
import controllers.GameController;
import controllers.PlayerController;
import models.Language;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ui.GUIController;
import controllers.GUIControllerStub;



@Disabled("Only used for acceptance test")
public class AcceptanceTest {
    private GameController gc;
    private GameStateDTO gs;
    private PlayerController pc;
    private GUIController gui;
    private FieldController fc;
    Language dansk = new Language();
    @BeforeEach
    public void beforeEach(){
        gui = new GUIController();
        gc = new GameController();
        gs = new GameStateDTO(gui);
        pc = new PlayerController();
        fc = new FieldController(dansk);

    }
    @Test
    public void AK5_6(){
        System.out.println("Test");
    }


    @Test
    public void AK7(){
        pc.addPlayer(0,"UFO","moneyTest",2);
        pc.addPlayer(1,"UFO","dummy1",2);
        pc.addPlayer(1,"UFO","dummy2",2);
        gc.TakeTurn(pc.getPlayerById(0));

    }
}