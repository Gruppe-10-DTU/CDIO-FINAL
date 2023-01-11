package controllers;

import models.Language;
import models.Player;
import models.fields.*;

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
        CSVMock.add(new ArrayList<>(Arrays.asList("I fængsel/På besøg","3","jail","1000","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Hvidovrevej","4","street","1200","1000","50","250","400","750","2250","6000","blue")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Fængsel","5","jail","0","","","","","","","","")));
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
    void playerPropertyValuesNoHouses() {

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

        //2 first cost 1200, 3 next cost 6000
        assertEquals(13200, fieldcontroller.playerPropertyValues(mockPlayer1));
        assertEquals(7200, fieldcontroller.playerPropertyValues(mockPlayer2));
    }

    @Test
    void playerPropertyValuesWithHouses() {

        boolean ownerPlayer1 = true;

        //place the players as owners
        for (Object field : fieldcontroller.fieldArrayList) {
            if ( field instanceof Street) {
                if (ownerPlayer1) {
                    ((Street) field).setOwner(mockPlayer1);
                    ((Street) field).setHouseAmount(2);
                    ownerPlayer1 = false;
                } else {
                    ((Street) field).setOwner(mockPlayer2);
                    ((Street) field).setHouseAmount(3);
                    ownerPlayer1 = true;
                }
            }
        }

        //2 first cost 1200, house 1000, 3 next cost 6000, house 4000
        //Player one 2 houses, player 2 3
        assertEquals(31200, fieldcontroller.playerPropertyValues(mockPlayer1));
        assertEquals(22200, fieldcontroller.playerPropertyValues(mockPlayer2));
    }
    @Test
    void playerPropertyValuesWithHousesAndBrewery() {

        boolean ownerPlayer1 = true;
        Brewery brewery = new Brewery();
        brewery.setPrice(3000);
        fieldcontroller.fieldArrayList.add(brewery);

        //place the players as owners
        for (Object field : fieldcontroller.fieldArrayList) {
            if ( field instanceof Street) {
                if (ownerPlayer1) {
                    ((Street) field).setOwner(mockPlayer1);
                    ((Street) field).setHouseAmount(2);
                    ownerPlayer1 = false;
                } else {
                    ((Street) field).setOwner(mockPlayer2);
                    ((Street) field).setHouseAmount(3);
                    ownerPlayer1 = true;
                }
            } else if (field instanceof Brewery) {
                ((Brewery) field).setOwner(mockPlayer1);
            }
        }

        //2 first cost 1200, house 1000, 3 next cost 6000, house 4000
        //Player one 2 houses, player 2 3
        assertEquals(34200, fieldcontroller.playerPropertyValues(mockPlayer1));
        assertEquals(22200, fieldcontroller.playerPropertyValues(mockPlayer2));
    }
    /*@Test
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
    }*/

}