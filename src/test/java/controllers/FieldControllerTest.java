package controllers;

import models.Language;
import models.Player;
import models.fields.Field;
import models.fields.Jail;

import models.fields.Start;
import models.fields.Street;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FieldControllerTest {

    Language language = new Language();
    ArrayList<ArrayList<String>> CSVMock = new ArrayList<>();
    Player mockPlayer1 = new Player(1, "player1");
    Player mockPlayer2 = new Player(2, "player2");

    FieldController fieldcontroller = new FieldController(language);

    @BeforeEach
    void setUp() {
        fieldcontroller.fieldArrayList.clear();

        CSVMock.add(new ArrayList<>(Arrays.asList("Start","0","start","","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Rødovrevej","1","street","1200","1000","50","250","750","2250","4000","6000","blue")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Prøv lykken","2","chance","","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("I fængsel/På besøg","3","jail","","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Hvidovrevej","4","street","1200","1000","50","250","400","750","2250","6000","blue")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Fængsel","5","jail","","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Amagertorv","6","street","6000","4000","550","2600","7800","18000","22000","25000","yellow")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Vimmelskaftet","7","street","6000","4000","550","2600","7800","18000","22000","25000","yellow")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Vimmelskaftet","8","street","6000","4000","550","2600","7800","18000","22000","25000","yellow")));
        fieldcontroller.createFieldArray(CSVMock);
    }



    @Test
    void construct() {

        assertEquals(9, fieldcontroller.fieldArrayList.size());
    }


    @Test
    void jailPlayer() {

        fieldcontroller.jailPlayer(mockPlayer1);

        int jailIndex = 0;


        for (Object field : fieldcontroller.fieldArrayList) {
            if ( field instanceof Jail) {
                jailIndex = ((Jail) field).getID();

                assertTrue(((Jail) field).getInJail().contains(mockPlayer1));
                break;
            }
        }
        assertEquals(jailIndex, mockPlayer1.getLocation());
    }


    @Test
    void FreePlayer() {
        //Jail the player
        fieldcontroller.jailPlayer(mockPlayer1);

        for (Object field : fieldcontroller.fieldArrayList) {
            if ( field instanceof Jail) {

                //Check that player was jailed
                assertTrue(((Jail) field).getInJail().contains(mockPlayer1));
                break;
            }
        }

        //Free the player
        fieldcontroller.freePlayer(mockPlayer1);

        for (Object field : fieldcontroller.fieldArrayList) {
            if ( field instanceof Jail) {
                //Check that player is no longer jailed
                assertFalse(((Jail) field).getInJail().contains(mockPlayer1));
                break;
            }
        }
    }

    @Test
    void playerPropertyValues() {

        boolean ownerPlayer1 = true;

        //place the players as owners
        for (Object field : fieldcontroller.fieldArrayList) {
            if ( field instanceof Street) {
                if (ownerPlayer1) {
                    ((Street) field).setOwner(mockPlayer1);
                    ownerPlayer1 = false;
                } else {
                    ((Street) field).setOwner(mockPlayer2);
                    ownerPlayer1 = true;
                }
            }
        }

        HashMap playerValues = fieldcontroller.playerPropertyValues();

        assertEquals(5, playerValues.get(mockPlayer1));
        assertEquals(3, playerValues.get(mockPlayer2));
    }

    @Test
    void moveToColor() {
        /*
        //Regular move
        int spaces1 = fieldcontroller.moveToColor("PINK", mockPlayer1);
        assertEquals(5, spaces1);

        //Move past start
        mockPlayer2.setLocation(5);
        int spaces2 = fieldcontroller.moveToColor("BROWN", mockPlayer2);
        assertEquals(5, spaces2);

         */
    }


    @Test
    void sameOwner() {
        Player player1 = new Player(0,"test");
        Field field;
        fieldcontroller.setOwner(player1, 8);

        Street property = (Street) fieldcontroller.getField(8);
        assertFalse(fieldcontroller.sameOwner(property));
        fieldcontroller.setOwner(player1, 7);
        assertTrue(fieldcontroller.sameOwner(property));

        //Reset
        fieldcontroller.setOwner(null, 7);
        fieldcontroller.setOwner(null, 8);
        //Other way

        fieldcontroller.setOwner(player1, 7);

        property = (Street) fieldcontroller.getField(7);
        assertFalse(fieldcontroller.sameOwner(property));
        fieldcontroller.setOwner(player1, 8);
        assertTrue(fieldcontroller.sameOwner(property));

        fieldcontroller.setOwner(null, 7);
        fieldcontroller.setOwner(null, 8);
    }

    @Test
    void testAllChoicesTest() {
        int counter = 0;
        for (Field field : fieldcontroller.fieldArrayList) {
            if ( field instanceof Street) {
                if (counter < 2) {
                    ((Street) field).setOwner(mockPlayer1);
                    counter++;
                } else {
                    ((Street) field).setOwner(mockPlayer2);
                }
            }
        }
        assertEquals(2, fieldcontroller.getFieldOtherPlayers(mockPlayer1).length);
        assertEquals(7, fieldcontroller.getFieldOtherPlayers(mockPlayer1)[0].getID() );
    }
}