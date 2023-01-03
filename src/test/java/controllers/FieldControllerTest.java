package controllers;

import models.Language;
import models.Player;
import models.fields.Field;
import models.fields.Jail;

import models.fields.Property;
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

        CSVMock.add(new ArrayList<>(Arrays.asList("Start")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Property","BROWN","3")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Empty")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Chance")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Jail")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Property","PINK","1")));
        CSVMock.add(new ArrayList<>(Arrays.asList("ToJail")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Property","RED","2")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Property","RED","2")));
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
            if ( field instanceof Property) {
                if (ownerPlayer1) {
                    ((Property) field).setOwner(mockPlayer1);
                    ownerPlayer1 = false;
                } else {
                    ((Property) field).setOwner(mockPlayer2);
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

        //Regular move
        int spaces1 = fieldcontroller.moveToColor("PINK", mockPlayer1);
        assertEquals(5, spaces1);

        //Move past start
        mockPlayer2.setLocation(5);
        int spaces2 = fieldcontroller.moveToColor("BROWN", mockPlayer2);
        assertEquals(5, spaces2);
    }


    @Test
    void sameOwner() {
        Player player1 = new Player(0,"test");
        Field field;
        fieldcontroller.setOwner(player1, 8);

        Property property = (Property) fieldcontroller.getField(8);
        assertFalse(fieldcontroller.sameOwner(property));
        fieldcontroller.setOwner(player1, 7);
        assertTrue(fieldcontroller.sameOwner(property));

        //Reset
        fieldcontroller.setOwner(null, 7);
        fieldcontroller.setOwner(null, 8);
        //Other way

        fieldcontroller.setOwner(player1, 7);

        property = (Property) fieldcontroller.getField(7);
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
            if ( field instanceof Property) {
                if (counter < 2) {
                    ((Property) field).setOwner(mockPlayer1);
                    counter++;
                } else {
                    ((Property) field).setOwner(mockPlayer2);
                }
            }
        }
        assertEquals(2, fieldcontroller.getFieldOtherPlayers(mockPlayer1).length);
        assertEquals(7, fieldcontroller.getFieldOtherPlayers(mockPlayer1)[0].getID() );
    }
}