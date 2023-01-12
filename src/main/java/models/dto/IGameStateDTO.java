package models.dto;

import controllers.DiceHolder;
import controllers.FieldController;
import controllers.PlayerController;
import models.Player;
import models.chanceCards.Deck;
import ui.GUIController;

public interface IGameStateDTO {
    Player getActivePlayer();
    GUIController getGuiController();
    FieldController getFieldController();
    PlayerController getPlayerController();
    DiceHolder getDiceHolder();
    Deck getChancecardDeck();
}
