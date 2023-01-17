package models.chanceCards;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChangeBalanceTest {
    GameStateDTO gameState;
    ChangeBalance card1;
    ChangeBalance card2;

    @BeforeEach
    void setUp() {
        Player player1 = new Player(0,"Player1");
        ArrayList<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(new Player(1,"player2"));
        gameState = new GameStateDTO(player1,otherPlayers);
        gameState.setPlayerController(new PlayerController());
        gameState.setGuiController(new GUIControllerStub());
        gameState.setFieldController(new FieldController());
        gameState.setChanceCardDeck(new Deck());
        card1 = new ChangeBalance("", "",-1000,false);
        card2 = new ChangeBalance("", "",1000,false);
    }

    @Test
    @DisplayName("Normal ChangeBalance cards")
    void chanceEffectNormal() {
        card1 = new ChangeBalance("", "",-1000,false);
        card2 = new ChangeBalance("", "",1000,false);
        int startMoney = gameState.getActivePlayer().getBalance();
        card1.chanceEffect(gameState);
        assertTrue(startMoney > gameState.getActivePlayer().getBalance());
        card2.chanceEffect(gameState);
        assertEquals(startMoney, gameState.getActivePlayer().getBalance());
    }
    @Test
    @DisplayName("Changebalance money from other players")
    void chanceEffectFromOthers() {
        card1 = new ChangeBalance("", "",1000,true);
        card1.chanceEffect(gameState);
        int player1money = gameState.getActivePlayer().getBalance();
        int player2Money = gameState.getOtherPlayers().get(0).getBalance();
        assertTrue(player1money > player2Money);
    }
}