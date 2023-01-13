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

class ChanceTaxTest {
    GameStateDTO gameState;
    Tax card1;
    Tax card2;

    @BeforeEach
    void setUp() {
        Player player1 = new Player(0,"Player1");
        ArrayList<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(new Player(1,"player2"));
        gameState = new GameStateDTO(player1, otherPlayers);
        gameState.setPlayerController(new PlayerController());
        gameState.setGuiController(new GUIControllerStub());
        gameState.setFieldController(new FieldController(new Language()));
        gameState.setChanceCardDeck(new Deck(new Language()));
        card1 = new Tax("Tax_1", "",800,2300);
        card2 = new Tax("tax_2", "",500,2000);
    }

    @Test
    @DisplayName("Correct calculation of player tax")
    void chanceEffect() {
        ((Street) gameState.getFieldController().getField(6)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(6)).setHouseAmount(3);
        ((Street) gameState.getFieldController().getField(8)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(8)).setHotel(true);
        ((Street) gameState.getFieldController().getField(9)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(9)).setHotel(true);
        card1.chanceEffect(gameState);
        assertEquals(23000,gameState.getActivePlayer().getBalance());

        ((Street) gameState.getFieldController().getField(18)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(18)).setHouseAmount(3);
        ((Street) gameState.getFieldController().getField(19)).setOwner(gameState.getActivePlayer());
        ((Street) gameState.getFieldController().getField(19)).setHotel(true);
        card2.chanceEffect(gameState);
        assertEquals(14000,gameState.getActivePlayer().getBalance());
    }
}