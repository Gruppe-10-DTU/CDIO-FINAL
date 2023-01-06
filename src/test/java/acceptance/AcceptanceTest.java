package acceptance;

import controllers.CheatDiceHolder;
import controllers.FieldController;
import controllers.GameController;
import controllers.PlayerController;
import models.Language;
import models.chanceCards.Deck;
import models.dto.GameStateDTO;
import models.fields.Street;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ui.GUIController;

@Disabled("Only used for acceptance test")
public class AcceptanceTest {
    private GameController gc;
    private GameStateDTO gs;
    private PlayerController pc;
    private FieldController fc;
    private Language language;
    private CheatDiceHolder dH;
    private Deck deck;
    private GUIController gui;



    @BeforeEach
    public void beforeAll() {
        language = new Language();
        fc = new FieldController(language);
        gui = new GUIController(fc.getFieldList());
        gs = new GameStateDTO(gui);

        gs.setFieldController(fc);
        dH = new CheatDiceHolder(2);
        gs.setDiceHolder(dH);
        pc = new PlayerController();
        gs.setPlayerController(pc);
        deck = new Deck(language);
        gc = new GameController(gs, language, deck);
    }
    @Test
    public void AK5_6(){
        dH.setRolls(5,5);
        pc.addPlayer(0, "car", "test",0);
        gui.setPlayers(pc.getPlayers());
        gc.takeTurn(pc.getPlayerById(0));
    }


    @Test
    public void AK7(){
        pc.addPlayer(0,"UFO","moneyTest",2);
        pc.addPlayer(1,"UFO","dummy1",2);
        gui.setPlayers(pc.getPlayers());
        gc.takeTurn(pc.getPlayerById(0));

    }

    @Test
    public void AK9() {
        pc.addPlayer(0,"UFO","renter",2);
        pc.addPlayer(1,"UFO","owner",1);
        gui.setPlayers(pc.getPlayers());
        Street street = (Street) fc.getField(3);
        street.setOwner(pc.getPlayerById(1));
        dH.setRolls(2,1);
        gc.takeTurn(pc.getPlayerById(0));
        gui.displayMsg("Test er ovre");

    }
}