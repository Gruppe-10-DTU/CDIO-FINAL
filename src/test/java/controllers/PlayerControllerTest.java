package controllers;

import junit.framework.TestCase;
import models.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}