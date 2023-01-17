package controllers;

import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import models.fields.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class FieldControllerTest {

    private static GameStateDTO gameStateDTO;
    private static FieldController fieldcontroller;
    private static GUIControllerStub guiController;
    private static PlayerController playerController;

    ArrayList<ArrayList<String>> CSVMock = new ArrayList<>();
    Player mockPlayer1 = new Player(1, "player1");
    Player mockPlayer2 = new Player(2, "player2");


    @BeforeEach
    void setUp() {
        Language language = new Language();
        fieldcontroller = new FieldController(language);
        guiController = new GUIControllerStub();
        playerController = new PlayerController();
        playerController.addPlayer(0, "car", "test1",1);
        playerController.addPlayer(1, "car", "test2",1);

        gameStateDTO = new GameStateDTO(guiController);
        gameStateDTO.setActivePlayer(playerController.getPlayerById(0));
        gameStateDTO.setPlayerController(playerController);
        gameStateDTO.setFieldController(fieldcontroller);

        gameStateDTO.getFieldController().fieldArrayList.clear();
        CSVMock.add(new ArrayList<>(Arrays.asList("Helsingør - Helsingborg","0","ferry","4000","","500","1000","2000","4000","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Start","1","start","","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Rødovrevej","2","street","1200","1000","50","250","750","2250","4000","6000","blue")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Hvidovrevej","3","street","1200","1000","50","250","400","750","2250","6000","blue")));
        CSVMock.add(new ArrayList<>(Arrays.asList("Prøv lykken","4","chance","","","","","","","","","")));
        CSVMock.add(new ArrayList<>(Arrays.asList("I fængsel/På besøg","5","jail","1000","","","","","","","","")));
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


    @Test
    void ownsColourGroup() {
        fieldcontroller.fieldArrayList.remove(9);
        //Note to whoever reads this. This method _ONLY_ works if the amount of fields is devisable by 5. That's why we remove a field.
        for(Object field : fieldcontroller.fieldArrayList){
            if(field instanceof Street){
                ((Street) field).setOwner(mockPlayer1);
            }
        }
        assertEquals(2, fieldcontroller.ownsColourGroup(mockPlayer1).size());
    }

    @Test
    void buildEqual() {
        fieldcontroller.fieldArrayList.remove(9);
        for(Object field : fieldcontroller.fieldArrayList){
            if(field instanceof Street){
                ((Street) field).setOwner(mockPlayer1);
            }
        }
        assertEquals(2,fieldcontroller.buildEqual(fieldcontroller.ownsColourGroup(mockPlayer1)).get("blue").length);
        ((Street) fieldcontroller.getField(2)).setHouseAmount(1);
        assertEquals(1,fieldcontroller.buildEqual(fieldcontroller.ownsColourGroup(mockPlayer1)).get("blue").length);
        ((Street) fieldcontroller.getField(3)).setHouseAmount(1);
        assertEquals(2,fieldcontroller.buildEqual(fieldcontroller.ownsColourGroup(mockPlayer1)).get("blue").length);

    }

    @Test
    void sellProperty() {
        fieldcontroller.fieldArrayList.remove(9);
        //Sells a house from a curated selection, curated by the max amount of house on colour group.
        for(Object field : fieldcontroller.fieldArrayList){
            if(field instanceof Street){
                ((Street) field).setOwner(mockPlayer1);
            }
        }
        ((Street) fieldcontroller.getField(2)).setHouseAmount(1);
        assertEquals(1,fieldcontroller.propertyWithBuilding(fieldcontroller.ownsColourGroup(mockPlayer1)).get("blue").length);
        ((Street) fieldcontroller.getField(3)).setHouseAmount(1);
        assertEquals(2,fieldcontroller.propertyWithBuilding(fieldcontroller.ownsColourGroup(mockPlayer1)).get("blue").length);
    }

    @Test
    void addBuilding() {
        fieldcontroller.fieldArrayList.remove(9);
        for(Object field : fieldcontroller.fieldArrayList){
            if(field instanceof Street){
                ((Street) field).setOwner(mockPlayer1);
            }
        }
        fieldcontroller.addBuilding(((Street) fieldcontroller.getField(2)));
        //Test to see if houses are correct
        assertEquals(1,((Street) fieldcontroller.getField(2)).getHouseAmount());
        //Test to see if we actually withdrew any money from the player
        assertEquals(29000,((Street) fieldcontroller.getField(2)).getOwner().getBalance());
        //Test to see if house pool is decreased
        assertEquals(31,fieldcontroller.getHousePool());
    }

    @Test
    void sellBuilding() {
        fieldcontroller.fieldArrayList.remove(9);
        for(Object field : fieldcontroller.fieldArrayList){
            if(field instanceof Street){
                ((Street) field).setOwner(mockPlayer1);
            }
        }
        ((Street) fieldcontroller.getField(2)).setHouseAmount(3);
        //Test to see if houses are being removed from the field when sold.
        fieldcontroller.sellBuilding(((Street) fieldcontroller.getField(2)),1);
        assertEquals(2,((Street) fieldcontroller.getField(2)).getHouseAmount());
        fieldcontroller.sellBuilding((Street) fieldcontroller.getField(2),2);
        assertEquals(0,((Street) fieldcontroller.getField(2)).getHouseAmount());
        //Test to see if player balance is increased when selling houses. Formula for selling houses is: (price/2)*amountToSell
        assertEquals(31500,((Street) fieldcontroller.getField(2)).getOwner().getBalance());
        //Test to see if housing pool is increased again.
        assertEquals(35,fieldcontroller.getHousePool());
    }
    @Test
    void testRemovePlayerFromProperties() {
        Player player = playerController.getPlayerById(0);
        for (Object field : fieldcontroller.fieldArrayList) {
            if ( field instanceof Property) {
               ((Property) field).setOwner(player);
            }
        }

        fieldcontroller.removePlayer(player);

        for (Object field : fieldcontroller.fieldArrayList) {
            if ( field instanceof Property) {
                assertNull(((Property) field).getOwner());
            }
        }
    }
}