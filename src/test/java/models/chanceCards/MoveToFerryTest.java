package models.chanceCards;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import models.fields.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MoveToFerryTest {

    GameStateDTO gameState;
    ChanceCard card1;
    ChanceCard card2;
    ChanceCard card3;

    @BeforeEach
    void setUp() {
        Player player1 = new Player(0,"Player1");
        ArrayList<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(new Player(1,"player2"));
        gameState = new GameStateDTO(player1, otherPlayers);
        gameState.setPlayerController(new PlayerController());
        gameState.setGuiController(new GUIControllerStub());
        gameState.setFieldController(new FieldController(new Language()));
        gameState.setChancecardDeck(new Deck(new Language()));
        card1 = new MoveToFerry("", "",2,false);
        card2 = new MoveToFerry("", "",2,false);
        card3 = new MoveToFerry("", "",1,true);
    }

    @Test
    void chanceEffect() {
        gameState.getActivePlayer().setLocation(3);
        int money = gameState.getActivePlayer().getBalance();
        GameStateDTO newState = card1.chanceEffect(gameState);
        ((Property)gameState.getFieldController().getField(5)).setOwner(gameState.getOtherPlayers().get(0));
        assertEquals(5, gameState.getActivePlayer().getLocation());
        assertTrue(money > gameState.getActivePlayer().getBalance());
    }
}