package models.dto;

import models.Player;
import ui.GUIController;

import java.util.ArrayList;
import java.util.Map;

public class GameStateDTO {
    private Player activePlayer;
    private ArrayList<Player> otherPlayers;
    private GUIController guiController;


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