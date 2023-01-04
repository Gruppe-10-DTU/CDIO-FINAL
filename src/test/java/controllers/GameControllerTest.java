package controllers;

import models.Language;
import models.Player;
import models.chanceCards.Deck;
import models.fields.Jail;
import models.fields.Start;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    GameController gameController;
    cheatDiceHolder diceHolder;
    PlayerController pc;
    FieldController fieldController;
    GUIControllerStub gui;
    Language language;
    Deck deck;
    @Test
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

    @Test
    void testFreeChoiceProperty() {
        Player testPlayer = new Player(0, "Test");
        assertNotEquals(testPlayer, ((Start.Property) fieldController.getField(23)).getOwner());
        gameController.characterSpecific(testPlayer);
        assertEquals(testPlayer, ((Start.Property) fieldController.getField(23)).getOwner());
    }

    @Test
    void testBuyFromOtherPlayerChanceCard() {
        Player testPlayer1 = new Player(0, "Test1");
        Player testPlayer2 = new Player(0, "Test2");
        Start.Property[] properties = fieldController.getFreeFields();
        for (Start.Property property : properties
             ) {
            property.setOwner(testPlayer2);
        }
        //Testing if player 2 buys a property from player 1
        gameController.characterSpecific(testPlayer1);
        //Test if balance was updated correctly
        assertEquals(25, testPlayer2.getBalance());
        assertEquals(15, testPlayer1.getBalance());

        //Test if ownership was transfered
        assertNotEquals(testPlayer2, ((Start.Property) fieldController.getField(23)).getOwner());
    }

    @Test
    void testNoMoreHouses() {
        Player testPlayer1 = new Player(0, "Test1");
        while(testPlayer1.decreaseSoldSign()){

        }
        gameController.characterSpecific(testPlayer1);
        assertNotEquals(testPlayer1, ((Start.Property) fieldController.getField(23)).getOwner());

    }

    @BeforeEach
    void setUp() {
        language = new Language();
        fieldController = new FieldController(language);
        gui = new GUIControllerStub(fieldController.getFieldList());
        pc = new PlayerController(gui.playerAmount("test"));
        deck = new Deck(language);
        diceHolder = new cheatDiceHolder();
        for (int i = 0; i < gui.playerAmount("test"); i++) {
            pc.addPlayer(i, gui.selectCharacter("test", "test"), gui.getName("test"),0);
        }

        gameController = new GameController(language, pc, fieldController, gui, deck, diceHolder);
    }
    @Test
    void testEqualBalance() {
        fieldController.setOwner(pc.getPlayers()[0],1);
        assertEquals(pc.getPlayers()[0].getIdentifier(), gameController.checkAllBalance());
    }

    @Test
    void testGamePropertiesLoadCorrectly() {
        Properties properties;

    }

    @Test
    void testZeroBalance() {
        Player[] players = pc.getPlayers();
        players[1].setBalance(-10);
        players[2].setBalance(-18);
         assertEquals(players[0].getIdentifier(), gameController.checkAllBalance());
    }
}