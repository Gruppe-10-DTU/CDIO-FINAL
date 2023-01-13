package models.fields;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.chanceCards.Deck;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChanceTest {

    private GameStateDTO gameState;

    @BeforeEach
    void setUp() {
        Language language = new Language();
        Deck deck = new Deck(language);
        FieldController fieldController = new FieldController(language);
        PlayerController playerController = new PlayerController();
        playerController.addPlayer(0,"car","player1",0);
        playerController.addPlayer(1,"car", "player2", 1);
        GUIControllerStub guiControllerStub = new GUIControllerStub();
        guiControllerStub.setButtonClicked(true);
        gameState = new GameStateDTO(guiControllerStub);
        gameState.setActivePlayer(playerController.getPlayerById(0));
        gameState.setChancecardDeck(deck);
        gameState.setFieldController(fieldController);
        gameState.setPlayerController(playerController);
    }

    @Test
    @DisplayName("Check the field effect is correctly executed")
    void fieldEffect() {
        /*        Test Tax cards         */
        ((Street) gameState.getFieldController().getField(6)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(6)).setHouseAmount(3);
        ((Street) gameState.getFieldController().getField(8)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(8)).setHotel(true);
        ((Street) gameState.getFieldController().getField(9)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(9)).setHotel(true);
        gameState.getActivePlayer().setLocation(2);
        gameState.getFieldController().landOnField(gameState);
        assertNotEquals(30000, gameState.getActivePlayer().getBalance());

        /*        Test Change balance cards         */
        gameState.getChanceCardDeck().rigDeck(1);
        gameState.getActivePlayer().setLocation(2);
        gameState.getFieldController().landOnField(gameState);
        assertNotEquals(30000, gameState.getActivePlayer().getBalance());

        /*        Test grant card         */
        gameState.getChanceCardDeck().rigDeck(24);
        gameState.getFieldController().landOnField(gameState);
        assertTrue(30000 > gameState.getActivePlayer().getBalance());

        /*        Test Move X Steps Cards         */
        gameState.getActivePlayer().setLocation(17);
        gameState.getFieldController().landOnField(gameState);
        assertNotEquals(17, gameState.getActivePlayer().getLocation());

        /*        Test Move to field cards         */
        gameState.getChanceCardDeck().rigDeck(8);
        gameState.getActivePlayer().setLocation(22);
        gameState.getFieldController().landOnField(gameState);
        assertNotEquals(22,gameState.getActivePlayer().getLocation());

        /*        Test move to ferry cards         */
        gameState.getChanceCardDeck().rigDeck(3);
        gameState.getActivePlayer().setLocation(22);
        gameState.getFieldController().landOnField(gameState);
        assertEquals(25, gameState.getActivePlayer().getLocation());

        /*        Test get out of Jail cards         */
        gameState.getChanceCardDeck().rigDeck(3);
        gameState.getActivePlayer().setLocation(36);
        gameState.getFieldController().landOnField(gameState);
        assertTrue(gameState.getActivePlayer().hasGetOutOfJail());
    }
}