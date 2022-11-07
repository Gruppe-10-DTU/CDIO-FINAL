package main.controllers;

import junit.framework.TestCase;
import main.models.Player;

public class GameControllerTest extends TestCase {

    public void testPlayerMove() {
                try {
                    //preamble. Makes necessery methods available to this test, even though they are considered private.
                    GameController gc = new GameController();
                    Player player1 = new Player("player 1");
                                /*
                                cheatDiceHolder diceHolder = new cheatDiceHolder();

                                Class c = gc.getClass();
                                java.lang.reflect.Field fieldDice = c.getDeclaredField("diceHolder");
                                fieldDice.setAccessible(true);
                                fieldDice.set(gc, diceHolder);

                                 */


                    //move 1
                    assertEquals(0,player1.getLocation());
                    gc.playerMove(player1,2);
                    assertEquals(2,player1.getLocation());
                    if(player1.getLocation() == 2){
                        System.out.println("Player moved 2 spaces");
                    }
                    gc.playerMove(player1,1);
                    assertEquals(3,player1.getLocation());
                    if(player1.getLocation() == 3){
                        System.out.println("Player moved 1 space");
                    }
                    gc.playerMove(player1,22);
                    assertEquals(1,player1.getLocation());
                    if(player1.getLocation() == 1){
                        System.out.println("Player passed start, position equals P-24.");
                    }
                } catch (Exception e) {
                    System.out.println("oops:" + e);
                }
            }
        }
