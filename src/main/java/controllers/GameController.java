package controllers;

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
    private GUIController guiController;
    private PlayerController playerController;
    private FieldController fieldController;
    private Deck deck;
    private Popup p;

    private boolean reverse;

    private boolean looper = false;

    private GameStateDTO gameState;

    public GameController() {
        fieldController = new FieldController();
        guiController = new GUIController(fieldController.getFieldList());
        deck = new Deck();
        diceHolder = new DiceHolder(StartValues.getInstance().getValue("diceAmount"));
        playerController = new PlayerController();
        deck.shuffle();
        gameState = new GameStateDTO(guiController);
        gameState.setFieldController(fieldController);
        gameState.setPlayerController(playerController);
        gameState.setChanceCardDeck(deck);
        gameState.setDiceHolder(diceHolder);
        gameState.setGameController(this);
        this.initialize();
    }

    public GameController(GameStateDTO gameState, Deck deck) {
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
        StringBuilder sb = new StringBuilder(Language.getInstance().getLanguageValue("colors"));
        List colors = Arrays.stream(String.valueOf(sb).split(",")).toList();
        int playerAmount = guiController.playerAmount(Language.getInstance().getLanguageValue("playerAmount"));
        for (int i = 0; i < playerAmount; i++) {
            name = guiController.getName(Language.getInstance().getLanguageValue("inputName"));
            while (!playerController.playerUnique(name)) {
                guiController.displayMsg(Language.getInstance().getLanguageValue("nameNotUnique"));
                name = guiController.getName(Language.getInstance().getLanguageValue("inputName"));
            }

            String character = guiController.selectCharacter(Language.getInstance().getLanguageValue("selectCharacter"), tokens);
            String color = guiController.selectCharacter(Language.getInstance().getLanguageValue("colorText"), String.valueOf(sb));
            sb.delete(sb.indexOf(color), sb.indexOf(color) + color.length() + 1);

            playerController.addPlayer(i, character, name, colors.indexOf(color));
        }
    }

    public void startGame(){
        reverse = guiController.getUserLeftButtonPressed(Language.getInstance().getLanguageValue("chooseDirection"), Language.getInstance().getLanguageValue("counterClockwise"), Language.getInstance().getLanguageValue("clockwise"));
        gameState.setReverse(reverse);

        guiController.setPlayers(playerController.getPlayers());

        Player currentPlayer;

        int playerAmount = playerController.getPlayers().length;
        do {
            //Håndterer problemet med at fjerne en spiller.
            while((currentPlayer = playerController.getPlayerById(turnCounter % playerAmount))==null){
                turnCounter++;
            }

            takeTurn(currentPlayer);

            if(playerController.getPlayerById(turnCounter % playerAmount)==null){
                guiController.removePlayer(currentPlayer, fieldController.removePlayer(currentPlayer));
            }
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
        if(placesToBuild.size() >= 1 && (fieldController.getHousePool() != 0 && fieldController.getHotelPool() != 0)) {
            looper = guiController.getUserLeftButtonPressed(Language.getInstance().getLanguageValue("canBuild", player.getIdentifier()), Language.getInstance().getLanguageValue( "ja"), Language.getInstance().getLanguageValue("nej"));
            boolean loopdeloop = true;
            //Hvor kan der bygges?
            while (looper && placesToBuild.size() >= 1) {
                if (!loopdeloop) {
                    looper = guiController.getUserLeftButtonPressed(Language.getInstance().getLanguageValue("canBuild", player.getIdentifier(), player.getIdentifier()), Language.getInstance().getLanguageValue( "ja"), Language.getInstance().getLanguageValue("nej"));
                    if(!looper) break;
                }
                loopdeloop = false;
                    String colorChosen = guiController.selectColorBuild(Language.getInstance().getLanguageValue("chooseColorOptions"), placesToBuild.keySet().toArray(String[]::new));
                    String whereToBuild = guiController.selectBuild(Language.getInstance().getLanguageValue("selectBuildingText","" + placesToBuild.get(colorChosen)[0].getHousePrice()), placesToBuild.get(colorChosen));
                    Street street = fieldController.getStreetFromString(whereToBuild);
                    if (player.getBalance() < street.getHousePrice()) {
                        looper = guiController.getUserLeftButtonPressed(Language.getInstance().getLanguageValue("lackingFunds"), Language.getInstance().getLanguageValue( "ja"), Language.getInstance().getLanguageValue("nej"));
                    } else {
                        if (street.getHousePrice() <= player.getBalance() && street.getHouseAmount() < 5) {
                           if(street.getHouseAmount() < 4 && fieldController.getHousePool() > 0) {
                               fieldController.addBuilding(street);
                               guiController.guiAddHouse(street, street.getHouseAmount());
                               guiController.updatePlayer(player);
                           } else if (street.getHouseAmount() < 4 && fieldController.getHousePool() == 0) {
                               guiController.displayMsg(Language.getInstance().getLanguageValue("noMoreHouse"));
                           } else if(street.getHousePrice()<= player.getBalance() && street.getHouseAmount() == 4 && fieldController.getHotelPool() > 0) {
                               fieldController.addBuilding(street);
                               guiController.guiAddHotel(street);
                               guiController.updatePlayer(player);
                           }else{
                               guiController.displayMsg(Language.getInstance().getLanguageValue("noMoreHotel"));
                           }
                        } else {
                            looper = guiController.getUserLeftButtonPressed(Language.getInstance().getLanguageValue("lackingFunds"), Language.getInstance().getLanguageValue( "ja"), Language.getInstance().getLanguageValue("nej"));
                        }
                    }
                    placesToBuild = fieldController.buildEqual(fieldController.ownsColourGroup(player));
                }
                }

            //Tjek jail
            if (fieldController.isJailed(player)) {
                fieldController.landOnField(gameState);
            }
            if (!(fieldController.isJailed(player))) {
                guiController.getRoll(Language.getInstance().getLanguageValue("rollText", player.getIdentifier()), Language.getInstance().getLanguageValue("rollButton"));
                diceHolder.roll();
                guiController.displayDice(diceHolder.getRolls());
                if (diceHolder.isEqual()) {
                    diceHolder.incrementSameRolls();
                } else {
                    diceHolder.setSameRolls(0);
                }
                if (diceHolder.getSameRolls() == 3) {
                    guiController.displayMsg(Language.getInstance().getLanguageValue("3doubles"));
                    fieldController.jailPlayer(player);
                    diceHolder.setSameRolls(0);
                } else {
                    boolean overStart = player.getLocation() + diceHolder.sum(reverse) > StartValues.getInstance().getValue("boardSize");
                    playerController.playerMove(player, diceHolder.sum(reverse));
                    guiController.movePlayer(player, reverse);
                    if (overStart) {
                        guiController.displayMsg(Language.getInstance().getLanguageValue("passStart", String.valueOf(StartValues.getInstance().getValue("passStartBonus"))));
                    }
                    fieldController.landOnField(gameState);
                }
                guiController.updatePlayer(player);
                guiController.updateBoard(playerController, fieldController);
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
        return playerController.getPlayers().length == 1;
    }
    public void winMsg() {
        String winner = playerController.getPlayers()[0].getIdentifier();
        guiController.displayMsgNoBtn(Language.getInstance().getLanguageValue("winner") + " " + winner);
        JFrame f = new JFrame("popup");
        JLabel l = new JLabel(Language.getInstance().getLanguageValue("winner") + " " + winner);
        PopupFactory pf = new PopupFactory();
        JPanel p2 = new JPanel();
        p2.setBackground(Color.red);
        p2.add(l);
        p = pf.getPopup(f, p2, 180, 100);
        JButton b = new JButton(Language.getInstance().getLanguageValue("endGame"));
        b.addActionListener(this);
        p2.add(b);
        p.show();
    }



}


