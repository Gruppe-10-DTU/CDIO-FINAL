package controllers;

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

    ArrayList<ArrayList<String>> CSVMock = new ArrayList<>();
    Player mockPlayer1 = new Player(1, "player1");
    Player mockPlayer2 = new Player(2, "player2");


    @BeforeEach
    void setUp() {
        CSVMock.add(new ArrayList<>(Arrays.asList("Start")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Property","BROWN","3")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Empty")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Chance")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Jail")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Property","PINK","1")));
        CSVMock.add(new ArrayList<>(Arrays.asList("ToJail")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Property","RED","2")));
    }


    @Test
    void construct() {
        FieldController mockFieldcontroller = new FieldController(CSVMock);
        assertEquals(8, mockFieldcontroller.fieldArrayList.size());

    }

    @Test
    void jailPlayer() {
        FieldController fieldcontroller = new FieldController(CSVMock);

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
        FieldController mockFieldcontroller = new FieldController(CSVMock);

        //Jail the player
        mockFieldcontroller.jailPlayer(mockPlayer1);

        for (Object field : mockFieldcontroller.fieldArrayList) {
            if ( field instanceof Jail) {

                //Check that player was jailed
                assertTrue(((Jail) field).getInJail().contains(mockPlayer1));
                break;
            }
        }

        //Free the player
        mockFieldcontroller.freePlayer(mockPlayer1);

        for (Object field : mockFieldcontroller.fieldArrayList) {
            if ( field instanceof Jail) {
                //Check that player is no longer jailed
                assertFalse(((Jail) field).getInJail().contains(mockPlayer1));
                break;
            }
        }
    }

    @Test
    void playerPropertyValues() {
        FieldController mockFieldcontroller = new FieldController(CSVMock);

        boolean ownerPlayer1 = true;

        //place the players as owners
        for (Object field : mockFieldcontroller.fieldArrayList) {
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

        HashMap playerValues = mockFieldcontroller.playerPropertyValues();

        assertEquals(5, playerValues.get(mockPlayer1));
        assertEquals(1, playerValues.get(mockPlayer2));
    }

    @Test
    void moveToColor() {
        FieldController mockFieldcontroller = new FieldController(CSVMock);

        //Regular move
        int spaces1 = mockFieldcontroller.moveToColor("PINK", mockPlayer1);
        assertEquals(5, spaces1);

        //Move past start
        mockPlayer2.setLocation(5);
        int spaces2 = mockFieldcontroller.moveToColor("BROWN", mockPlayer2);
        assertEquals(4, spaces2);
    }


}