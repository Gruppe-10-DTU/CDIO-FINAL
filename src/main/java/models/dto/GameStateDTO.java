package models.dto;

import controllers.DiceHolder;
import controllers.FieldController;
import models.Player;
import ui.GUIController;

import java.util.ArrayList;
import java.util.Map;

public class GameStateDTO {
    private Player activePlayer;
    private ArrayList<Player> otherPlayers;
    private GUIController guiController;

    private FieldController fieldController;

    public void setFieldController(FieldController fieldController) {
        this.fieldController = fieldController;
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    public void setDiceHolder(DiceHolder diceHolder) {
        this.diceHolder = diceHolder;
    }

    public DiceHolder getDiceHolder() {
        return diceHolder;
    }

    private DiceHolder diceHolder;

    public void setGuiController(GUIController guiController) {
        this.guiController = guiController;
    }

    public GUIController getGuiController() {
        return guiController;
    }

    public GameStateDTO(Player activePlayer, ArrayList<Player> otherPlayers) {
        this.activePlayer = activePlayer;
        this.otherPlayers = otherPlayers;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public void setOtherPlayers(ArrayList<Player> otherPlayers) {
        this.otherPlayers = otherPlayers;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public ArrayList<Player> getOtherPlayers() {
        return otherPlayers;
    }


}
