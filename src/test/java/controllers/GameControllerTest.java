package controllers;

import models.Language;
import models.chanceCards.Deck;
import models.dto.GameStateDTO;
import models.fields.Street;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GUIController;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GameControllerTest {
    GameController gameController;
    CheatDiceHolder diceHolder;
    PlayerController pc;
    FieldController fieldController;
    GUIControllerStub gui;
    Language language;
    Deck deck;
    GameStateDTO gameState;
   /* @Test
    void testGetOutOfJail(){
        Player testPlayer = new Player(0, "Test");
        //Set player in jail
        testPlayer.setLocation(6);
        //Make sure the next roll is 0
        diceHolder.setNextRoll(6);
        fieldController.jailPlayer(testPlayer);
        gameController.TakeTurn(testPlayer);
        assertEquals(18, testPlayer.getBalance());
        assertEquals(12, testPlayer.getLocation());
        assertFalse(((Jail) fieldController.getField(6)).isInJail(testPlayer));
    }

    */
/*
    @Test
    void testFreeChoiceProperty() {
        Player testPlayer = new Player(0, "Test");
        assertNotEquals(testPlayer, ((Street) fieldController.getField(23)).getOwner());
        gameController.characterSpecific(testPlayer);
        assertEquals(testPlayer, ((Street) fieldController.getField(23)).getOwner());
    }
/*
    @Test
    void testBuyFromOtherPlayerChanceCard() {
        Player testPlayer1 = new Player(0, "Test1");
        Player testPlayer2 = new Player(0, "Test2");
        Street[] properties = fieldController.getFreeFields();
        for (Street property : properties
             ) {
            property.setOwner(testPlayer2);
        }
        //Testing if player 2 buys a property from player 1
        gameController.characterSpecific(testPlayer1);
        //Test if balance was updated correctly
        assertEquals(25, testPlayer2.getBalance());
        assertEquals(15, testPlayer1.getBalance());

        //Test if ownership was transfered
        assertNotEquals(testPlayer2, ((Street) fieldController.getField(23)).getOwner());
    }

 */
/*
    @Test
    void testNoMoreHouses() {
        Player testPlayer1 = new Player(0, "Test1");
        while(testPlayer1.decreaseSoldSign()){

        }
        gameController.characterSpecific(testPlayer1);
        assertNotEquals(testPlayer1, ((Street) fieldController.getField(23)).getOwner());

    }

 */

    @BeforeEach
    void setUp() {
        language = new Language();
        fieldController = new FieldController(language);
        gui = new GUIControllerStub(fieldController.getFieldList());
        gameState = new GameStateDTO(gui);
        diceHolder = new CheatDiceHolder(2);
        gameState.setDiceHolder(diceHolder);
        pc = new PlayerController();
        gameState.setPlayerController(pc);
        deck = new Deck(language);
        gameState.setChancecardDeck(deck);
        gameState.setFieldController(fieldController);
        for (int i = 0; i < gui.playerAmount("test"); i++) {
            pc.addPlayer(i, gui.selectCharacter("test", "test"), gui.getName("test"),0);
        }

        gameController = new GameController(gameState, language, deck);
    }
    /*
    @Test
    void testEqualBalance() {
        fieldController.setOwner(pc.getPlayers()[0],1);
        assertEquals(pc.getPlayers()[0].getIdentifier(), gameController.checkAllBalance());
    }

     */


    @Test
    void win() {
        for (int i = pc.getAvailablePlayers().size(); i > 1 ; i--) {
            assertNotEquals(true,gameController.win());
            pc.removePlayer(i);
        }
        assertNotEquals(false,gameController.win());
    }

    @Test
    void testExtraTurn() {
        pc.getPlayerById(0).setLocation(0);
        diceHolder.setRolls(3,3);
        diceHolder.setIsEqualAmount(0);
        pc.getPlayerById(1).setBalance(-29999);
        ((Street)fieldController.getField(6)).setOwner(pc.getPlayerById(0));
        gameController.startGame();
        assertNotEquals(6, pc.getPlayerById(0).getLocation());
        assertEquals(12, pc.getPlayerById(0).getLocation());


    }
    /*
    @Test
    void testZeroBalance() {
        Player[] players = pc.getPlayers();
        players[1].setBalance(-10);
        players[2].setBalance(-18);
         assertEquals(players[0].getIdentifier(), gameController.checkAllBalance());
    }

 */



    }
