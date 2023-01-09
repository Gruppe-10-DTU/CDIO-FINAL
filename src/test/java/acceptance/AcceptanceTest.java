package acceptance;
import controllers.CheatDiceHolder;
import controllers.FieldController;
import controllers.GameController;
import controllers.PlayerController;
import models.Language;
import models.chanceCards.Deck;
import models.dto.GameStateDTO;
import models.fields.Brewery;
import models.fields.Ferry;
import models.fields.Jail;
import models.fields.Street;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
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
    public void beforeEach() {
        language = new Language();
        fc = new FieldController(language);
        gui = new GUIController(fc.getFieldList(), language);
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

    @Test
    public void AK8(){
        pc.addPlayer(0,"UFO","buyPropertyTest",2);
        pc.addPlayer(1,"UFO","dummy1",2);
        gui.setPlayers(pc.getPlayers());
        dH.setRolls(3,2);
        gc.takeTurn(pc.getPlayerById(0));
        gui.displayMsg("Test er ovre");
    }


    @Test
    public void AK10(){
        pc.addPlayer(0,"UFO","jailNoMoneyTest",2);
        pc.addPlayer(1,"UFO","PassStartTest",2);
        Jail jail = (Jail) fc.getField(10);

        gui.setPlayers(pc.getPlayers());
        dH.setRolls(3,2);
        pc.getPlayerById(1).setLocation(35);
        gui.updatePlayer(pc.getPlayerById(1));
        gc.takeTurn(pc.getPlayerById(1));
        /*
        pc.getPlayerById(0).setLocation(28);
        gui.updatePlayer(pc.getPlayerById(0));
        dH.setNextRoll(2);
        gc.TakeTurn(pc.getPlayerById(0));

         */
    }

    @Test
    public void AK11_12(){
        pc.addPlayer(0,"UFO","buyBrewery/Docks-Test",2);
        pc.addPlayer(1,"UFO","Dummy1",2);

        gui.setPlayers(pc.getPlayers());
        Brewery brew = (Brewery) fc.getField(12);
        Ferry ferry = (Ferry) fc.getField(15);
        dH.setRolls(6,6);
        gc.takeTurn(pc.getPlayerById(0));
        dH.setRolls(6,6);
        gc.takeTurn(pc.getPlayerById(1));
        dH.setRolls(2,1);
        gc.takeTurn(pc.getPlayerById(0));
        dH.setRolls(1,2);
        gc.takeTurn(pc.getPlayerById(1));

        gui.displayMsg("Test er ovre");
    }

    @Test
    public void AK13(){


    }


    @Test
    public void AK15(){
        pc.addPlayer(0,"UFO","InterfaceTest",2);
        gui.setPlayers(pc.getPlayers());
        gc.startGame();

    }

    @Test
    public void AK16_17(){

    }
}