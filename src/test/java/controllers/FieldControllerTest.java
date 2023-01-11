package controllers;

import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import models.fields.Field;
import models.fields.Jail;

import models.fields.Start;
import models.fields.Street;
import models.fields.Ferry;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FieldControllerTest {

    private static GameStateDTO gameStateDTO;
    private static FieldController fieldController;
    private static GUIControllerStub guiController;
    private static PlayerController playerController;

    ArrayList<ArrayList<String>> CSVMock = new ArrayList<>();
    Player mockPlayer1 = new Player(1, "player1");
    Player mockPlayer2 = new Player(2, "player2");


    @BeforeEach
    void setUp() {
        Language language = new Language();
        fieldController = new FieldController(language);
        guiController = new GUIControllerStub();
        playerController = new PlayerController();
        playerController.addPlayer(0, "car", "test1",1);
        playerController.addPlayer(1, "car", "test2",1);

        gameStateDTO = new GameStateDTO(guiController);
        gameStateDTO.setActivePlayer(playerController.getPlayerById(0));
        gameStateDTO.setPlayerController(playerController);
        gameStateDTO.setFieldController(fieldController);

        gameStateDTO.getFieldController().fieldArrayList.clear();
        CSVMock.add(new ArrayList<>(Arrays.asList("Helsingør - Helsingborg","0","ferry","4000","","500","1000","2000","4000","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Start","1","start","","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Rødovrevej","2","street","1200","1000","50","250","750","2250","4000","6000","blue")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Prøv lykken","3","chance","","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("I fængsel/På besøg","4","jail","1000","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Hvidovrevej","5","street","1200","1000","50","250","400","750","2250","6000","blue")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Fængsel","6","jail","0","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Amagertorv","7","street","6000","4000","550","2600","7800","18000","22000","25000","yellow")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Vimmelskaftet","8","street","6000","4000","550","2600","7800","18000","22000","25000","yellow")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Vimmelskaftet","9","street","6000","4000","550","2600","7800","18000","22000","25000","yellow")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Mols-Linien","10","ferry","4000","","500","1000","2000","4000","","","")));
        gameStateDTO.getFieldController().createFieldArray(CSVMock);


    }



    @Test
    void construct() {

        assertEquals(11, gameStateDTO.getFieldController().fieldArrayList.size());
    }


    @Test
    void jailPlayer() {

        gameStateDTO.getFieldController().jailPlayer(mockPlayer1);

        int jailIndex = 0;


        for (Object field : gameStateDTO.getFieldController().fieldArrayList) {
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
        gameStateDTO.getFieldController().jailPlayer(mockPlayer1);

        for (Object field : gameStateDTO.getFieldController().fieldArrayList) {
            if ( field instanceof Jail) {

                //Check that player was jailed
                assertTrue(((Jail) field).getInJail().contains(mockPlayer1));
                break;
            }
        }

        //Free the player
        gameStateDTO.getFieldController().freePlayer(mockPlayer1);

        for (Object field : gameStateDTO.getFieldController().fieldArrayList) {
            if ( field instanceof Jail) {
                //Check that player is no longer jailed
                assertFalse(((Jail) field).getInJail().contains(mockPlayer1));
                break;
            }
        }
    }

    @Test
    void ferryOwnersAll() {
        Ferry ferry1 = (Ferry) gameStateDTO.getFieldController().getField(0);
        ferry1.setOwner(playerController.getPlayerById(1));
        Ferry ferry2 = (Ferry) gameStateDTO.getFieldController().getField(10);
        ferry2.setOwner(playerController.getPlayerById(1));

        int owned = gameStateDTO.getFieldController().ferrysOwned(playerController.getPlayerById(1), 0, 2);

        assertEquals(2, owned);
    }

    @Test
    void ferryOwners1() {
        Ferry ferry1 = (Ferry) gameStateDTO.getFieldController().getField(0);
        ferry1.setOwner(playerController.getPlayerById(1));

        int owned = gameStateDTO.getFieldController().ferrysOwned(playerController.getPlayerById(1), 0, 2);

        assertEquals(1, owned);
    }

    /*
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

     */


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