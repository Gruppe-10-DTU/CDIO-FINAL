package models.dto;

import controllers.DiceHolder;
import controllers.FieldController;
import controllers.PlayerController;
import models.Player;
import models.chanceCards.Deck;
import ui.GUIController;

import java.util.ArrayList;

public class GameStateDTO implements IGameStateDTO {
    private Player activePlayer;
    private ArrayList<Player> otherPlayers;
    private GUIController guiController;

    private FieldController fieldController;

    private PlayerController playerController;
    private Deck chancecardDeck;

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

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
    public GameStateDTO(GUIController guiController){
        this.guiController = guiController;
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

    public void setChancecardDeck(Deck chancecardDeck) {
        this.chancecardDeck = chancecardDeck;
    }

    public Deck getChancecardDeck(){
        return this.chancecardDeck;
    }


}
