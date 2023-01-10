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
        gameState = new GameStateDTO(playerController.getPlayerById(0), playerController.otherPlayers(1));
        gameState.setChancecardDeck(deck);
        gameState.setFieldController(fieldController);
        gameState.setPlayerController(playerController);
        GUIControllerStub guiControllerStub = new GUIControllerStub();
        gameState.setGuiController(guiControllerStub);
    }

    @Test
    @DisplayName("Check the field effect is correctly executed")
    void fieldEffect() {
        /*        Test Change balance cards         */
        gameState.getActivePlayer().setLocation(2);
        gameState.getFieldController().landOnField(gameState);
        assertNotEquals(30000, gameState.getActivePlayer().getBalance());

        /*        Test Move X Steps Cards         */
        gameState.getChancecardDeck().rigDeck(21);
        gameState.getActivePlayer().setLocation(17);
        gameState.getFieldController().landOnField(gameState);
        assertNotEquals(17, gameState.getActivePlayer().getLocation());
        gameState.getActivePlayer().setLocation(17);

        /*        Test Move to field cards         */
        gameState.getChancecardDeck().rigDeck(8);
        gameState.getActivePlayer().setLocation(22);
        gameState.getFieldController().landOnField(gameState);
        assertNotEquals(22,gameState.getActivePlayer().getLocation());
        gameState.getActivePlayer().setLocation(22);

        /*        Test get out of Jail cards         */
        gameState.getChancecardDeck().rigDeck(3);
        gameState.getFieldController().landOnField(gameState);
        assertTrue(gameState.getActivePlayer().hasGetOutOfJail());
    }
}