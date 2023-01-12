package models.chanceCards;

import controllers.FieldController;
import controllers.GUIControllerStub;
import controllers.PlayerController;
import controllers.StartValues;
import models.Character;
import models.Language;
import models.Player;
import models.dto.GameStateDTO;
import models.fields.Jail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MoveToFieldTest {
    GameStateDTO gameState;
    MoveToField card1;
    MoveToField card2;
    MoveToField card3;

    @BeforeEach
    void setUp() {
        Player player1 = new Player(0,"Player1");
        ArrayList<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(new Player(1,"player2"));
        gameState = new GameStateDTO(player1,otherPlayers);
        gameState.setPlayerController(new PlayerController());
        gameState.setGuiController(new GUIControllerStub());
        gameState.setFieldController(new FieldController(new Language()));
        gameState.setChancecardDeck(new Deck(new Language()));
        card1 = new MoveToField("", "",true,37);
        card2 = new MoveToField("", "",true,24);
        card3 = new MoveToField("GoToJail", "",false,10);
    }

    @Test
    @DisplayName("Test that player i correctly affected by card")
    void chanceEffect() {
        gameState.getActivePlayer().setLocation(2);
        int expectedBalance = gameState.getActivePlayer().getBalance();
        GameStateDTO newState = card1.chanceEffect(gameState);
        assertEquals(37,gameState.getActivePlayer().getLocation());
        //assertEquals(expectedBalance,gameState.getActivePlayer().getBalance());

        gameState.getActivePlayer().setLocation(39);
        expectedBalance = gameState.getActivePlayer().getBalance() + StartValues.getInstance().getValue("passStartBonus");
        newState = card2.chanceEffect(gameState);
        assertEquals(24,gameState.getActivePlayer().getLocation());
        //assertEquals(expectedBalance,gameState.getActivePlayer().getBalance());

        gameState.getActivePlayer().setLocation(24);
        expectedBalance = gameState.getActivePlayer().getBalance();
        newState = card3.chanceEffect(gameState);
        assertEquals(10,gameState.getActivePlayer().getLocation());
        assertEquals(expectedBalance,gameState.getActivePlayer().getBalance());
        Jail jail = (Jail) gameState.getFieldController().getField(10);
        assertTrue(jail.isInJail(gameState.getActivePlayer()));
    }
}