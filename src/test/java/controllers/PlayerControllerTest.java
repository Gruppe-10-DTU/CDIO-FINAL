package controllers;

import junit.framework.TestCase;
import models.Language;
import models.Player;
import models.fields.Field;
import models.fields.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerControllerTest extends TestCase {
@Test
@DisplayName("playerMove test")
    public void testPlayerMove() {

                    //Loads necessary controllers. Creates players..
                    PlayerController pc = new PlayerController(2);
                    Player player1 = new Player(0,"Player 1");
                    Player player2 = new Player(1,"Player 2");


                    //move 1
                    assertEquals(0,player1.getLocation());
                    pc.playerMove(player1,2);
                    assertEquals(2,player1.getLocation());

                    pc.playerMove(player1,1);
                    assertEquals(3,player1.getLocation());

                    pc.playerMove(player1,22);
                    assertEquals(1,player1.getLocation());

                    pc.playerMove(player2,2);
                    assertEquals(2,player2.getLocation());


    }
    @Test
    @DisplayName("Unique Player identifier test & addPlayer")
    public void testUniquePlayer(){
        String name = "Åge";
        PlayerController pc = new PlayerController(2);
        pc.addPlayer(0,"UFO","Svend");
        assertEquals(true,pc.playerUnique(name));
        pc.addPlayer(1,"UFO","Åge");
        assertEquals(false, pc.playerUnique(name));

    }
    @Test
    @DisplayName("payMoney test")
    public void testPayMoney(){
    PlayerController pc = new PlayerController(2);
    pc.addPlayer(0,"UFO","Svend");
    pc.addPlayer(1,"UFO","Åge");
    pc.payMoney(pc.getPlayerById(0),2);
    assertEquals(18,pc.getPlayerById(0).getBalance());
    pc.payMoney(pc.getPlayerById(1),-2);
    assertEquals(22,pc.getPlayerById(1).getBalance());
    }

    @Test
    @DisplayName("getRent test")
    public void testGetRent(){
    //Relevant constructers
    PlayerController pc = new PlayerController(2);
    Language language = new Language();
    FieldController fc = new FieldController(language);
    ArrayList<ArrayList<String>> CSVMock = new ArrayList<>();
    //Create test-fields to test getRent.
    CSVMock.add(new ArrayList<>(Arrays.asList("Property","RED","1")));
    CSVMock.add(new ArrayList<>(Arrays.asList("Property","RED","1")));
    //Add players
    pc.addPlayer(0,"UFO","Svend");
    pc.addPlayer(1,"UFO","Åge");
    //Give ownership to certain player
    fc.setOwner(pc.getPlayerById(0),1);
    //Move other player onto field, use getRent function and then see if he paid rent for 1 field (1st field rent i 1)
    pc.getPlayerById(1).setLocation(1);
    //Have to cast field to property so that I can use the property variable in the getRent function. Meh...
    Field field = fc.getField(pc.getPlayerById(1).getLocation());
    Property property = (Property) field;
    pc.getRent(pc.getPlayerById(1),property,fc.sameOwner(property));
    assertEquals(19,pc.getPlayerById(1).getBalance());
    //Now testing if the player owns both fields, will the rent increase?
    fc.setOwner(pc.getPlayerById(0),2);
    pc.getPlayerById(1).setLocation(1,1);
    pc.getRent(pc.getPlayerById(1),property,fc.sameOwner(property));
    assertEquals(17,pc.getPlayerById(1).getBalance());
    //Luckily it works. Thank god.

    }


}