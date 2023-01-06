package acceptance;

import controllers.CheatDiceHolder;
import controllers.FieldController;
import controllers.GameController;
import controllers.PlayerController;
import gui_main.GUI;
import models.Language;
import models.Player;
import models.chanceCards.Deck;
import models.dto.GameStateDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ui.GUIController;

@Disabled("Only used for acceptance test")
public class AcceptanceTest {
    private GameController gameController;
    private GameStateDTO gameState;
    private PlayerController playerController;
    private FieldController fieldController;
    private Language language;
    private CheatDiceHolder diceHolder;
    private Deck deck;
    private GUIController guiController;

    @BeforeEach
    public void beforeAll(){
        language = new Language();
        fieldController = new FieldController(language);
        guiController = new GUIController(fieldController.getFieldList(), language);
        gameState = new GameStateDTO(guiController);

        gameState.setFieldController(fieldController);
        diceHolder = new CheatDiceHolder(2);
        gameState.setDiceHolder(diceHolder);
        playerController = new PlayerController();
        gameState.setPlayerController(playerController);
        deck = new Deck(language);
        gameController = new GameController(gameState, language, deck);
    }
    @Test
    public void AK5_6(){
        diceHolder.setRolls(5,5);
        playerController.addPlayer(0, "car", "test",0);
        guiController.setPlayers(playerController.getPlayers());
        diceHolder.setNextRoll(10);
        gameController.TakeTurn(playerController.getPlayerById(0));
    }
}