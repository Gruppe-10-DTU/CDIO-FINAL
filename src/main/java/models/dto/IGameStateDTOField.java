package models.dto;

import controllers.FieldController;
import models.Player;
import ui.GUIController;

public interface IGameStateDTOField {
    Player getActivePlayer();
    GUIController getGuiController();
    FieldController getFieldController();
}
