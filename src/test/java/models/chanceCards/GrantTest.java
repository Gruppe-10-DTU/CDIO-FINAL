package models.chanceCards;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import models.fields.Street;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GrantTest {

    GameStateDTO gameState;
    Grant card1;
    Grant card2;

    @BeforeEach
    void setup(){
        Player player1 = new Player(0,"Player1");
        ArrayList<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(new Player(1,"player2"));
        gameState = new GameStateDTO(player1,otherPlayers);
        gameState.setPlayerController(new PlayerController());
        gameState.setGuiController(new GUIControllerStub());
        gameState.setFieldController(new FieldController());
        gameState.setChanceCardDeck(new Deck());
        card1 = new Grant("", "",15000,40000);
        card2 = new Grant("", "",15000,40000);
    }

    @Test
    @DisplayName("correctly awards bonus when owning no properties")
    void chanceEffectNoProperties() {
        int startMoney = gameState.getActivePlayer().getBalance();
        card1.chanceEffect(gameState);
        assertEquals(startMoney, gameState.getActivePlayer().getBalance());
        gameState.getActivePlayer().setBalance(-20000);
        card2.chanceEffect(gameState);
        assertTrue(startMoney < gameState.getActivePlayer().getBalance());

    }
    @Test
    @DisplayName("Correctly awards bonus when owning properties")
    void chanceEffectWithProperties(){
        ((Street) gameState.getFieldController().getField(6)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(6)).setHouseAmount(3);
        ((Street) gameState.getFieldController().getField(8)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(8)).setHotel(true);
        ((Street) gameState.getFieldController().getField(9)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(9)).setHotel(true);

        int startMoney = gameState.getActivePlayer().getBalance();
        card1.chanceEffect(gameState);
        assertEquals(startMoney, gameState.getActivePlayer().getBalance());
        gameState.getActivePlayer().setBalance(-25000);
        card2.chanceEffect(gameState);
        assertTrue(startMoney < gameState.getActivePlayer().getBalance());
    }
}