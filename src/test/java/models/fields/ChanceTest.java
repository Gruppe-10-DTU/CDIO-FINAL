package models.fields;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.chanceCards.Deck;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
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
    void fieldEffect() {
        /*        Test Change balance cards         */
        gameState.getActivePlayer().setLocation(2);
        for (int i = 0; i < 22; i++) {
            int playerMoneyBeforeCard = gameState.getActivePlayer().getBalance();
            gameState.getFieldController().landOnField(gameState);
            assertNotEquals(playerMoneyBeforeCard, gameState.getActivePlayer().getBalance());
        }
        /*        Test Move X Steps Cards         */
        gameState.getActivePlayer().setLocation(7);
        for (int i = 0; i < 3; i++) {
            gameState.getFieldController().landOnField(gameState);
            assertNotEquals(7, gameState.getActivePlayer().getLocation());
            gameState.getActivePlayer().setLocation(7);
        }
        /*        Test Move to field cards         */
        gameState.getActivePlayer().setLocation(22);
        for (int i = 0; i < 10; i++) {
            gameState.getFieldController().landOnField(gameState);
            assertNotEquals(22,gameState.getActivePlayer().getLocation());
            gameState.getActivePlayer().setLocation(22);
        }
        /*        Test get out of Jail cards         */
        gameState.getFieldController().landOnField(gameState);
        assertNotNull(gameState.getActivePlayer().getGetOutOfJail());
    }
}