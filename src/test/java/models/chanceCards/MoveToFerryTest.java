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
        card1 = new MoveToFerry("", "",2,true);
        card2 = new MoveToFerry("", "",1,true);
    }

    @Test
    void chanceEffect() {
        //Checks with rent multiplier
        gameState.getActivePlayer().setLocation(7);
        ((Property)gameState.getFieldController().getField(15)).setOwner(gameState.getOtherPlayers().get(0));
        card1.chanceEffect(gameState);
        assertEquals(15, gameState.getActivePlayer().getLocation());
        assertEquals(29000, gameState.getActivePlayer().getBalance());
        assertEquals(31000,gameState.getOtherPlayers().get(0).getBalance());

        //Checks passing start and with no rent multiplier (note other player has 2 ferries in this test)
        gameState.getActivePlayer().setLocation(36);
        ((Property)gameState.getFieldController().getField(5)).setOwner(gameState.getOtherPlayers().get(0));
        card2.chanceEffect(gameState);
        assertEquals(5,gameState.getActivePlayer().getLocation());
        assertEquals(32000, gameState.getActivePlayer().getBalance());
        assertEquals(32000,gameState.getOtherPlayers().get(0).getBalance());

    }
}