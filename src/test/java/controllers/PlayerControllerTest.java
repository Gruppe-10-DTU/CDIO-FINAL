package controllers;

import junit.framework.TestCase;
import models.Language;
import models.fields.Field;
import models.fields.Street;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerControllerTest extends TestCase {
@Test
@DisplayName("playerMove test")
    public void testPlayerMove() {

                    //Loads necessary controllers. Creates players..
                    PlayerController pc = new PlayerController();
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
                    pc.playerMove(pc.getPlayerById(0),38);
                    assertEquals(1,pc.getPlayerById(0).getLocation());
                    //Test to see if balance is changed
                    assertEquals(34000,pc.getPlayerById(0).getBalance());
                    //Test to see it can differentiate between player 1 and 2's location and balance.
                    pc.playerMove(pc.getPlayerById(1),2);
                    assertEquals(2,pc.getPlayerById(1).getLocation());
                    assertEquals(30000,pc.getPlayerById(1).getBalance());

    }
    @Test
    @DisplayName("Unique Player identifier test & addPlayer")
    public void testUniquePlayer(){
        String name = "Åge";
        PlayerController pc = new PlayerController();
        pc.addPlayer(0,"UFO","Svend",0);
        assertEquals(true,pc.playerUnique(name));
        pc.addPlayer(1,"UFO","Åge",0);
        assertEquals(false, pc.playerUnique(name));

    }

    @Test
    void removePlayer() {
    PlayerController pc = new PlayerController();
    pc.addPlayer(0,"UFO","Svend",0);
    pc.addPlayer(1,"UFO","Åge",0);
    pc.addPlayer(2,"UFO","Åge",0);
    assertEquals(3,pc.getPlayers().length);
    pc.removePlayer(0);
    assertEquals(2,pc.getPlayers().length);
    pc.removePlayer(1);
    assertEquals(1,pc.getPlayers().length);
    }
}