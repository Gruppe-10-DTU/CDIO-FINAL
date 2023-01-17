package acceptance;
import controllers.*;
import models.Language;
import models.chanceCards.Deck;
import models.chanceCards.GetOutOfJail;
import models.dto.GameStateDTO;
import models.fields.*;
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
    private CheatDiceHolder dH;
    private Deck deck;
    private GUIController gui;



    @BeforeEach
    public void beforeEach() {
        fc = new FieldController();
        gui = new GUIController(fc.getFieldList());
        gs = new GameStateDTO(gui);

        gs.setFieldController(fc);
        dH = new CheatDiceHolder(2);
        gs.setDiceHolder(dH);
        pc = new PlayerController();
        gs.setPlayerController(pc);
        deck = new Deck();
        gc = new GameController(gs, deck);
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
        Start start = (Start) fc.getField(0);
        Ferry ferry = (Ferry) fc.getField(35);
        Brewery brew = (Brewery) fc.getField(28);
        gui.setPlayers(pc.getPlayers());
        dH.setRolls(3,2);

        pc.getPlayerById(1).setLocation(35);
        gui.updatePlayer(pc.getPlayerById(1));
        gc.takeTurn(pc.getPlayerById(1));

        pc.getPlayerById(0).setLocation(28);
        gui.updatePlayer(pc.getPlayerById(0));
        dH.setRolls(1,1);
        gc.takeTurn(pc.getPlayerById(0));
        gui.displayMsg("Testen er overstået. Spiller modtog penge på start, og spiller fik ikke penge ved at gå i fængsel.");
    }

    @Test
    public void AK11_12(){
        pc.addPlayer(0,"UFO","buyBrewery/Docks-Test",2);
        pc.addPlayer(1,"UFO","Dummy1",2);
        gs.setReverse(false);

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

        gui.displayMsg("Test er ovre.");
    }

    @Test
    public void AK13(){
        pc.addPlayer(0,"UFO","GoToJailTest",2);
        pc.addPlayer(1,"UFO","Dummy1",2);
        gui.setPlayers(pc.getPlayers());
        Jail jail = (Jail) fc.getField(10);
        pc.getPlayerById(0).setLocation(28);
        gui.updatePlayer(pc.getPlayerById(0));
        dH.setRolls(1,1);
        gc.takeTurn(pc.getPlayerById(0));

        gui.displayMsg("Test er ovre");
    }
    
    @Test
    public void AK14(){
        pc.addPlayer(0,"UFO","getOutOfJail",2);
        pc.addPlayer(1,"UFO","Dummy1",3);
        gui.setPlayers(pc.getPlayers());
        pc.getPlayerById(0).setLocation(28);
        dH.setRolls(1,1);
        ToJail toJail = (ToJail) fc.getField(30);
        Jail jail = (Jail) fc.getField(10);
        pc.getPlayerById(0).addGetOutOfJail(new GetOutOfJail("outOfJail","outOfJail"));
        gui.updatePlayer(pc.getPlayerById(0));
        gc.takeTurn(pc.getPlayerById(0));
        gui.updatePlayer(pc.getPlayerById(0));
        dH.setRolls(1,2);
        gc.takeTurn(pc.getPlayerById(0));
        dH.setRolls(2,2);
        gc.takeTurn(pc.getPlayerById(0));
    }

    @Test
    public void AK15(){
        pc.addPlayer(0,"UFO","InterfaceTest",2);
        gui.setPlayers(pc.getPlayers());

        gc.startGame();

    }

    @Test
    public void AK16_17(){
        pc.addPlayer(0,"UFO","WinGameNotificationTest",2);
        pc.addPlayer(1,"UFO","RemovePlayerFromGameTest",3);
        gui.setPlayers(pc.getPlayers());
        pc.getPlayerById(1).setBalance(-30000);
        gui.updatePlayer(pc.getPlayerById(1));
        dH.setRolls(2,1);
        gc.takeTurn(pc.getPlayerById(0));
        dH.setRolls(1,2);
        gc.takeTurn(pc.getPlayerById(1));
        gc.winMsg();
        gui.displayMsg("placeholder");
    }

    @Test
    public void AK18(){
        pc.addPlayer(0,"UFO","ChanceCardEffectTest",2);
        pc.addPlayer(1,"UFO","Dummy1",3);
        gui.setPlayers(pc.getPlayers());

        Chance chance = (Chance) fc.getField(2);
        Deck deck = new Deck();
        gs.setChanceCardDeck(deck);
        dH.setRolls(1,1);
        gc.takeTurn(pc.getPlayerById(0));
        gui.displayMsg("Testen er nu overstået");
    }

    @Test
    void AK19() {
        pc.addPlayer(0,"UFO","EkstraSlag",2);
        pc.addPlayer(1,"UFO","TestPerson2",3);
        pc.getPlayerById(1).setBalance(-29999);

        dH.setRolls(3,3);
        dH.setIsEqualAmount(1);
        gc.startGame();
        gui.displayMsg("Testen er nu overstået");
    }

    @Test
    void AK20(){
        pc.addPlayer(0,"UFO","StatiskSkat",2);
        pc.addPlayer(1,"UFO","DynamiskSkat",3);
        pc.getPlayerById(0).setLocation(34);
        pc.getPlayerById(1).setBalance(-29999);
        dH.setRolls(1,3);
        gc.startGame();
        gui.displayMsg("Testen er nu overstået");
    }

    @Test
    void AK27(){
        pc.addPlayer(0,"UFO","Player1",2);
        pc.addPlayer(1,"UFO","Player2",3);
        pc.getPlayerById(1).setBalance(-2);

        dH.setRolls(2,3);
        gc.startGame();
        gui.displayMsg("Testen er nu overstået");
    }

    @Test
    void AK31(){
        pc.addPlayer(0,"UFO","fængsel",2);
        pc.addPlayer(1,"UFO","taber",3);
        pc.getPlayerById(1).setBalance(-29999);

        dH.setRolls(2,2);
        dH.setIsEqualAmount(4);
        gc.startGame();
        gui.displayMsg("Testen er nu overstået");
    }

    @Test
    void AK22(){
        pc.addPlayer(0,"UFO","BuildHouseTest",2);
        pc.addPlayer(1,"UFO","Dummy1",3);
        gui.setPlayers(pc.getPlayers());
        gui.updatePlayer(pc.getPlayerById(0));
        pc.getPlayerById(0).setLocation(39);
        gui.updatePlayer(pc.getPlayerById(0));
        dH.setRolls(1,1);
        gc.takeTurn(pc.getPlayerById(0));
        gc.takeTurn(pc.getPlayerById(0));
        pc.getPlayerById(0).setLocation(39);
        gui.updatePlayer(pc.getPlayerById(0));
        gc.takeTurn(pc.getPlayerById(0));
        pc.getPlayerById(1).setLocation(39);
        gc.takeTurn(pc.getPlayerById(1));
        gui.displayMsg("Testen er ovre");
    }
    @Test
    void AK26(){
        pc.addPlayer(0,"UFO","OwnerOfBigProperties",2);
        pc.addPlayer(1,"UFO","bankruptPlayer",3);
        gui.setPlayers(pc.getPlayers());

        pc.getPlayerById(0).setLocation(39);
        gui.updatePlayer(pc.getPlayerById(0));

        dH.setRolls(1,1);
        gc.takeTurn(pc.getPlayerById(0));
        gc.takeTurn(pc.getPlayerById(0));

        pc.getPlayerById(1).setLocation(35);
        gui.updatePlayer(pc.getPlayerById(1));
        gc.takeTurn(pc.getPlayerById(1));
        gc.takeTurn(pc.getPlayerById(1));
        gc.takeTurn(pc.getPlayerById(1));
        gc.takeTurn(pc.getPlayerById(0));
        pc.getPlayerById(1).setBalance(-pc.getPlayerById(1).getBalance());
        gc.takeTurn(pc.getPlayerById(1));
        gui.displayMsg("Testen er ovre");
    }

    @Test
    public void AK21() {
        pc.addPlayer(0,"UFO","renter",2);
        pc.addPlayer(1,"UFO","owner",1);
        gui.setPlayers(pc.getPlayers());
        Street street1 = (Street) fc.getField(1);
        street1.setOwner(pc.getPlayerById(1));
        Street street2 = (Street) fc.getField(3);
        street2.setOwner((pc.getPlayerById(1)));

        gui.updatePlayer(pc.getPlayerById(0));
        gui.updateBoard(pc, fc);

        dH.setRolls(2,1);
        //pc.getPlayerById(0).setLocation(3);
        gc.takeTurn(pc.getPlayerById(0));
        gui.displayMsg("Test er ovre");
    }

    @Test
    public void AK23() {
        pc.addPlayer(0,"UFO","renter",2);
        gui.setPlayers(pc.getPlayers());
        Street blue1 = (Street) fc.getField(1);
        blue1.setOwner(pc.getPlayerById(0));
        blue1.setHouseAmount(4);
        Street blue2 = (Street) fc.getField(3);
        blue2.setOwner(pc.getPlayerById(0));
        blue2.setHouseAmount(3);
        Street pink1 = (Street) fc.getField(6);
        pink1.setOwner(pc.getPlayerById(0));
        pink1.setHouseAmount(4);
        Street pink2 = (Street) fc.getField(8);
        pink2.setOwner(pc.getPlayerById(0));
        pink2.setHouseAmount(4);
        Street pink3 = (Street) fc.getField(9);
        pink3.setOwner(pc.getPlayerById(0));
        pink3.setHouseAmount(4);
        Street green1 = (Street) fc.getField(11);
        green1.setOwner(pc.getPlayerById(0));
        green1.setHouseAmount(4);
        Street green2 = (Street) fc.getField(13);
        green2.setOwner(pc.getPlayerById(0));
        green2.setHouseAmount(4);
        Street green3 = (Street) fc.getField(14);
        green3.setOwner(pc.getPlayerById(0));
        green3.setHouseAmount(4);
        Street grey1 = (Street) fc.getField(16);
        grey1.setOwner(pc.getPlayerById(0));
        Street grey2 = (Street) fc.getField(18);
        grey2.setOwner(pc.getPlayerById(0));
        Street grey3 = (Street) fc.getField(19);
        grey3.setOwner(pc.getPlayerById(0));

        fc.setHousePool(1);

        gui.updatePlayer(pc.getPlayerById(0));
        gui.updateBoard(pc, fc);

        dH.setRolls(2,1);
        gc.takeTurn(pc.getPlayerById(0));
        gui.displayMsg("Test er ovre");
    }


}