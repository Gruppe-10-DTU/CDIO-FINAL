package models.chanceCards;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GrantTest {

    GameStateDTO gameState;
    Grant card1;
    Grant card2;


    @Test
    @DisplayName("correctly awards bonus")
    void chanceEffect() {
        Player player1 = new Player(0,"Player1");
        ArrayList<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(new Player(1,"player2"));
        gameState = new GameStateDTO(player1,otherPlayers);
        gameState.setPlayerController(new PlayerController());
        gameState.setGuiController(new GUIControllerStub());
        gameState.setFieldController(new FieldController(new Language()));
        gameState.setChancecardDeck(new Deck(new Language()));
        card1 = new Grant("", "",15000,40000);
        card2 = new Grant("", "",15000,40000);

        int startMoney = player1.getBalance();
        card1.chanceEffect(gameState);
        assertEquals(startMoney, gameState.getActivePlayer().getBalance());
        gameState.getActivePlayer().setBalance(-20000);
        card2.chanceEffect(gameState);
        assertTrue(startMoney < gameState.getActivePlayer().getBalance());

    }
}