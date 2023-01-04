package controllers;

import junit.framework.TestCase;
import models.Language;
import models.Player;
import models.fields.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import models.chanceCards.CharacterSpecific;
import models.*;
import models.Character;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerControllerTest extends TestCase {
@Test
@DisplayName("playerMove test")
    public void testPlayerMove() {

                    //Loads necessary controllers. Creates players..
                    PlayerController pc = new PlayerController(2);
                    pc.addPlayer(0,"UFO","Svend",0);
                    pc.addPlayer(1,"UFO","Åge",0);




                    //Testing start location is at start:
                    assertEquals(0,pc.getPlayerById(0).getLocation());

                    //Move player 1
                    pc.playerMove(pc.getPlayerById(0),2);
                    assertEquals(2,pc.getPlayerById(0).getLocation());

                    pc.playerMove(pc.getPlayerById(0),1);
                    assertEquals(3,pc.getPlayerById(0).getLocation());
                    //Move player 1 across start
                    pc.playerMove(pc.getPlayerById(0),22);
                    assertEquals(1,pc.getPlayerById(0).getLocation());
                    //Test to see if balance is changed
                    assertEquals(22,pc.getPlayerById(0).getBalance());
                    //Test to see it can differentiate between player 1 and 2's location and balance.
                    pc.playerMove(pc.getPlayerById(1),2);
                    assertEquals(2,pc.getPlayerById(1).getLocation());
                    assertEquals(20,pc.getPlayerById(1).getBalance());

    }
    @Test
    @DisplayName("Unique Player identifier test & addPlayer")
    public void testUniquePlayer(){
        String name = "Åge";
        PlayerController pc = new PlayerController(2);
        pc.addPlayer(0,"UFO","Svend",0);
        assertEquals(true,pc.playerUnique(name));
        pc.addPlayer(1,"UFO","Åge",0);
        assertEquals(false, pc.playerUnique(name));

    }
    /*
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
        pc.addPlayer(0,"UFO","Svend",0);
        pc.addPlayer(1,"UFO","Åge",0);
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
        //Testing to see if player has to pay on own property
        pc.getPlayerById(0).setLocation(1);
        pc.getRent(pc.getPlayerById(0),property,fc.sameOwner(property));
        assertEquals(23,pc.getPlayerById(0).getBalance());
        //Testing to see if player balance too low to pay rent.
        pc.getPlayerById(1).setBalance(-17);
        assertEquals(false,pc.getRent(pc.getPlayerById(1),property,fc.sameOwner(property)));
        //Luckily it works. Thank god.

    }

     */

    @Test
    void removePlayer() {
    PlayerController pc = new PlayerController(2);
    pc.addPlayer(0,"UFO","Svend",0);
    pc.addPlayer(1,"UFO","Åge",0);
    assertEquals(2,pc.getPlayers().length);
    pc.removePlayer(0);
    assertEquals(1,pc.getPlayers().length);
    }
}