package controllers;

import models.chanceCards.*;
import models.*;
import models.dto.GameStateDTO;
import models.fields.Street;
import ui.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class GameController implements ActionListener {
    private DiceHolder diceHolder;
    private int turnCounter = 0;
    private boolean isOver = false;
    private Language language;
    private GUIController guiController;
    private PlayerController playerController;
    private Player currentPlayer;
    private FieldController fieldController;
    private Deck deck;
    private Popup p;

    private boolean looper = false;

    private GameStateDTO gameState;

    public GameController() {
        language = new Language(System.getProperty("user.language"));
        fieldController = new FieldController(language);
        guiController = new GUIController(fieldController.getFieldList(), language);
        deck = new Deck(language);
        diceHolder = new DiceHolder(StartValues.getInstance().getValue("diceAmount"));
        playerController = new PlayerController();
        deck.shuffle();
        gameState = new GameStateDTO(guiController);
        gameState.setFieldController(fieldController);
        gameState.setPlayerController(playerController);
        gameState.setChancecardDeck(deck);
        gameState.setDiceHolder(diceHolder);
        this.initialize();
    }

    public GameController(GameStateDTO gameState, Language language, Deck deck) {
        this.language = language;
        this.playerController = gameState.getPlayerController();
        this.fieldController = gameState.getFieldController();
        this.guiController = gameState.getGuiController();
        this.deck = deck;
        this.diceHolder = gameState.getDiceHolder();
        deck.shuffle();
        this.gameState = gameState;
    }

    public void initialize() {
        String name;
        String tokens = "Car,Tractor,Racecar,UFO";
        StringBuilder sb = new StringBuilder(language.getLanguageValue("colors"));
        List colors = Arrays.stream(String.valueOf(sb).split(",")).toList();
        int playerAmount = guiController.playerAmount(language.getLanguageValue("playerAmount"));
        for (int i = 0; i < playerAmount; i++) {
            name = guiController.getName(language.getLanguageValue("inputName"));
            while (!playerController.playerUnique(name)) {
                guiController.displayMsg(language.getLanguageValue("nameNotUnique"));
                name = guiController.getName(language.getLanguageValue("inputName"));
            }

            String character = guiController.selectCharacter(language.getLanguageValue("selectCharacter"), tokens);
            String color = guiController.selectCharacter(language.getLanguageValue("colorText"), String.valueOf(sb));
            sb.delete(sb.indexOf(color), sb.indexOf(color) + color.length() + 1);

            playerController.addPlayer(i, character, name, colors.indexOf(color));
        }
    }

    public void startGame(){
        guiController.setPlayers(playerController.getPlayers());

        int playerAmount = playerController.getPlayers().length;
        int jailCounter = 0;
        do {
            //Håndterer problemet med at fjerne en spiller.
            while((currentPlayer = playerController.getPlayerById(turnCounter % playerAmount))==null){
                turnCounter++;
            }

            takeTurn(currentPlayer);

            if(diceHolder.getSameRolls()==0){
                turnCounter++;
            }
        }while (!win());
        winMsg();
    }

    /**
     * Logic to handle a players turn
     * @param player Active player
     */
    public void takeTurn(Player player) {
        gameState.setActivePlayer(player);
        gameState.setOtherPlayers(playerController.otherPlayers(player.getID()));
        Map<String,Street[]> ownsGroup = fieldController.ownsColourGroup(player);
        Map<String,Street[]> placesToBuild = fieldController.buildEqual(ownsGroup);
        //Tjek huskøb
        if(placesToBuild.size() >= 1) {
            boolean looper = guiController.yesnoSelection(language.getLanguageValue("canBuildHouses"));
            boolean loopdeloop = true;
            //Hvor kan der bygges?
            while (looper && placesToBuild.size()>=1) {
                if(!loopdeloop){
                    looper = guiController.yesnoSelection(language.getLanguageValue("canBuildHouses"));
                    if (!looper) break;
                }
                loopdeloop = false;
                String colorChosen = guiController.selectColorBuild(language.getLanguageValue( "chooseColorOptions"), placesToBuild.keySet().toArray(String[]::new));
                String whereToBuild = guiController.selectBuild(language.getLanguageValue( "selectBuildingText"), placesToBuild.get(colorChosen));
                if (!(player.getBalance() >= fieldController.getStreetFromString(whereToBuild).getHousePrice())) {
                    looper = guiController.yesnoSelection(language.getLanguageValue("lackingFunds"));
                } else {
                    if (fieldController.getStreetFromString(whereToBuild).getHousePrice() <= player.getBalance() && fieldController.getStreetFromString(whereToBuild).getHouseAmount() < 4) {
                        fieldController.addHouse(fieldController.getStreetFromString(whereToBuild));
                        guiController.guiAddHouse(fieldController.getStreetFromString(whereToBuild),fieldController.getStreetFromString(whereToBuild).getHouseAmount());
                        guiController.updatePlayer(player);
                    } else {
                        looper = guiController.yesnoSelection(language.getLanguageValue("lackingFunds"));
                    }
                }
                placesToBuild = fieldController.buildEqual(fieldController.ownsColourGroup(player));
            }
        }
        //Tjek jail
        if(fieldController.isJailed(player)) {
            fieldController.landOnField(gameState);
        }
        if(!(fieldController.isJailed(player))) {
            guiController.getRoll(language.getLanguageValue("rollText", player.getIdentifier()), language.getLanguageValue("rollButton"));
            diceHolder.roll();
            guiController.displayDice(diceHolder.getRolls());
            if(diceHolder.isEqual()) {
                diceHolder.incrementSameRolls();
            }else {
                diceHolder.setSameRolls(0);
            }
            if (diceHolder.getSameRolls()==3){
                guiController.displayMsg("Ulovligheder! Du har rullet ens 3 gange i træk og skal derfor i fængsel.");
                fieldController.jailPlayer(currentPlayer);
                diceHolder.setSameRolls(0);
            }else{
                boolean overStart = player.getLocation() + diceHolder.sum() > StartValues.getInstance().getValue("boardSize");
                playerController.playerMove(player, diceHolder.sum());
                guiController.movePlayer(player);
                if (overStart) {
                    guiController.displayMsg(language.getLanguageValue("passStart", String.valueOf(StartValues.getInstance().getValue("passStartBonus"))));
                }
                fieldController.landOnField(gameState);
            }
            guiController.updatePlayer(player);
            guiController.updateBoard(playerController,fieldController);
        }
    }

    /**
     * Listener for popup close button
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        p.hide();
        guiController.endGame();
        System.exit(0);
    }

    public boolean win() {
        return playerController.getAvailablePlayers().size() == 1;
    }
    public void winMsg() {
        String winner = playerController.getPlayers()[0].getIdentifier();
        guiController.displayMsgNoBtn(language.getLanguageValue("winner") + " " + winner);
        JFrame f = new JFrame("popup");
        JLabel l = new JLabel(language.getLanguageValue("winner") + " " + winner);
        PopupFactory pf = new PopupFactory();
        JPanel p2 = new JPanel();
        p2.setBackground(Color.red);
        p2.add(l);
        p = pf.getPopup(f, p2, 180, 100);
        JButton b = new JButton(language.getLanguageValue("endGame"));
        b.addActionListener(this);
        p2.add(b);
        p.show();
    }

}


