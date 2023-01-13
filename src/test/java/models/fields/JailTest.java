package models.fields;

import controllers.*;
import models.Language;
import models.chanceCards.Deck;
import models.chanceCards.GetOutOfJail;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JailTest {
    GameStateDTO gameState;
    PlayerController playerController;
    FieldController fieldController;
    Jail jail;

    @BeforeEach
    void setUp() {
        playerController = new PlayerController();
        playerController.addPlayer(0,"car","player1",0);
        playerController.addPlayer(1,"car", "player2", 1);
        fieldController = new FieldController(new Language());
        jail = (Jail) fieldController.getField(10);

    }

    @Test
    @DisplayName("Check that there is a list of jailed players")
    void getInJail() {
        assertNotNull(jail.getInJail());
    }

    @Test
    @DisplayName("Check if player is jailed")
    void isInJail() {
        assertFalse(jail.isInJail(playerController.getPlayerById(0)));
        assertFalse(jail.isInJail(playerController.getPlayerById(1)));
    }

    @Test
    @DisplayName("Set a player in jail")
    void setInJailAdd() {
        jail.setInJailAdd(playerController.getPlayerById(0));
        assertTrue(jail.getInJail().size() > 0);
    }

    @Test
    @DisplayName("Remove a player from jail")
    void setInJailRemove() {

        jail.setInJailAdd(playerController.getPlayerById(0));
        assertTrue(jail.isInJail(playerController.getPlayerById(0)));
        jail.setInJailRemove(playerController.getPlayerById(0));
        assertEquals(0, jail.getInJail().size());
    }

    @Test
    @DisplayName("Check that the player can be released if they are jailed")
    void fieldEffect() {
        gameState = new GameStateDTO(new GUIControllerStub());
        gameState.setPlayerController(playerController);
        gameState.setFieldController(fieldController);
        gameState.setDiceHolder(new CheatDiceHolder(2));
        gameState.setChanceCardDeck(new Deck(new Language()));
        gameState.setActivePlayer(playerController.getPlayerById(0));


        /*      Check the pay out of jail option    */
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
        jail.fieldEffect(gameState);
        assertFalse(jail.isInJail(gameState.getActivePlayer()));
        assertEquals(29000, gameState.getActivePlayer().getBalance());


        /*      Checks the roll option              */
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
        gameState.getActivePlayer().setBalance(-28900);
        ((CheatDiceHolder)gameState.getDiceHolder()).setRolls(5,5);
        jail.fieldEffect(gameState);
        assertFalse(jail.isInJail(gameState.getActivePlayer()));



        /*      Checks the card option              */
        gameState.getFieldController().jailPlayer(gameState.getActivePlayer());
        gameState.getActivePlayer().addGetOutOfJail(new GetOutOfJail("GetOutOfJail", "Get Out of jail free"));
        jail.fieldEffect(gameState);
        assertFalse(jail.isInJail(gameState.getActivePlayer()));
        assertFalse(gameState.getActivePlayer().hasGetOutOfJail());


    }
}