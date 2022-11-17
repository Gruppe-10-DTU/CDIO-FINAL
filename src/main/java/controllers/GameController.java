package controllers;

import chanceCards.*;
import chanceCards.Choice;
import models.*;
import models.fields.Field;
import models.fields.Property;
import ui.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class GameController implements ActionListener {
    private DiceHolder diceHolder = new DiceHolder(1);
    private int turnCounter = 0;
    private boolean isOver = false;
    private Language language;
    private GUIController guiController;
    private PlayerController playerController;
    private Player currentPlayer;
    private FieldController fieldController;
    private Deck deck;
    private Popup p;

    public GameController() {
        language = new Language(System.getProperty("user.language"));

        fieldController = new FieldController(language);
        guiController = new GUIController(fieldController.getFieldList());
        deck = new Deck();
        deck.shuffle();
        int playerAmount = guiController.playerAmount();
        playerController = new PlayerController(playerAmount);
        String name;
        String originalName = "car, racecar, ufo, tractor";
        StringBuilder sb = new StringBuilder("Car,Tractor,Racecar,UFO");

        for (int i = 0; i < playerAmount; i++) {

            name = guiController.getName("inputName");
            while (!playerController.playerUnique(name)) {
                guiController.displayMsg("nameNotUnique");
                name = guiController.getName("inputName");
            }

            String character = guiController.selectCharacter("selectCharacter", String.valueOf(sb));
            sb.delete(sb.indexOf(character), sb.indexOf(character) + character.length() + 1);

            playerController.addPlayer(i, character, name);
        }
        guiController.setPlayers(playerController.getPlayers());
        while (!isOver) {
            this.currentPlayer = playerController.getPlayerById(turnCounter);
            TakeTurn(currentPlayer);
        }

    }

    private void EndGame() {
        String endWinner = checkAllBalance();
        isOver = true;
        guiController.displayMsgNoBtn(language.getLanguageValue("winner") + " " + endWinner);

        JFrame f = new JFrame("popup");
        JLabel l = new JLabel(language.getLanguageValue("winner") + " " + endWinner);
        PopupFactory pf = new PopupFactory();
        JPanel p2 = new JPanel();
        p2.setBackground(Color.red);
        p2.add(l);
        p = pf.getPopup(f, p2, 180, 100);
        JButton b = new JButton(language.getLanguageValue("endGame"));
        b.addActionListener(this);
        p2.add(b);
        p.show();

        for (Player player: playerController.getPlayers()) {
            System.out.println(player.getBalance());
        }
    }

    public void TakeTurn(Player player) {
        diceHolder.roll();
        guiController.displayDice(diceHolder.getRolls());
        if (player.getLocation() + diceHolder.sum() >= 24) {
            guiController.displayMsg(language.getLanguageValue("passStart"));
        }
        playerController.playerMove(player, diceHolder.sum());
        guiController.updatePlayer(player);
        Field field = fieldController.getField(player.getLocation());
        //Choose logic based on the field type
        switch (field.getClass().getSimpleName()) {
            case "Property": {
                Property property = (Property) field;
                if(property.getOwner() == null && player.decreaseSoldSign()){
                    guiController.displayMsg("fieldBuy");
                    if (player.setBalance(-property.getPrice())) {
                        fieldController.setOwner(player, property.getID());
                        guiController.updateField(property);
                        guiController.updatePlayer(player);
                    } else {
                        EndGame();
                    }
                } else if (player.getSoldSign() < 0) {
                    guiController.displayMsg("You can't buy this since you've run out of houses");
                } else {
                    guiController.displayMsg("fieldRent");
                    if (!playerController.getRent(player, property)) {
                        EndGame();
                    } else {
                        guiController.updatePlayer(property.getOwner());
                    }
                }
                break;
            }
            case "Chance": {
                guiController.displayMsg(language.getLanguageValue("fieldChance"));
                takeChance();
                break;
            }
            case "Start": {
                guiController.displayMsg(language.getLanguageValue("fieldLandStart"));
                break;
            }
            case "Empty": {
                guiController.displayMsg(language.getLanguageValue("fieldFreeParking"));
                break;
            }
            case "ToJail": {
                guiController.displayMsg("fieldGoToJail");
                fieldController.jailPlayer(player);
                guiController.updatePlayer(player);
            }
            default: {
                break;
            }
        }
        turnCounter++;
    }
    public void takeChance(){
        ChanceCard card = deck.drawCard();
        String type = card.getType().replaceAll("class chanceCards.", "");
        guiController.showChanceCard(card.getDescription());
        String option1;
        String option2;
        String choice;
        switch (type){
            case "CharacterSpecific":
                CharacterSpecific csCard = (CharacterSpecific) card;
                break;
            case "ChangeBalance":
                ChangeBalance cbCard = (ChangeBalance) card;
                int value = cbCard.getEffect();
                if(cbCard.getFromOthers()){
                    for (Player player: playerController.getPlayers()) {
                        player.setBalance(-1 * value);
                    }
                    value *= playerController.getPlayers().length;
                }
                currentPlayer.setBalance(value);
                break;
            case "Choice":
                chanceCards.Choice chCard = (Choice) card;
                option1 = language.getLanguageValue("MoveXFields", String.valueOf(chCard.getMove()));
                option2 = language.getLanguageValue("ccDrawAgain");
                choice = guiController.showChanceCardChoice(language.getLanguageValue("ccChoice"), option1, option2);
                if(choice.equals(option1)){
                    playerController.playerMove(currentPlayer, chCard.getMove());
                } else if (choice.equals(option2)) {
                    takeChance();
                }
                break;
            case "GetOutOfJail":
                GetOutOfJail goojCard = (GetOutOfJail) card;
                break;
            case "MoveToColour":
                MoveToColour mtcCard = (MoveToColour) card;
                option1 = language.getLanguageValue(mtcCard.getColour_1().toUpperCase());
                option2 = language.getLanguageValue(mtcCard.getColour_2().toUpperCase());
                choice = guiController.showChanceCardChoice(language.getLanguageValue("ccChoice"), option1, option2);
                if(choice.equals(option1)){
                    fieldController.moveToColor(option1, currentPlayer);
                } else if (choice.equals(option2)) {
                    fieldController.moveToColor(option2, currentPlayer);
                }
                break;
            case "MoveToField":
                MoveToField mtfCard = (MoveToField) card;

                break;
            case "MoveXSteps":
                MoveXSteps mxsCard = (MoveXSteps) card;
                break;

        }
    }


    public int sum() {
        return diceHolder.sum() - 1;
    }

    public Integer getTurnCounter() {
        return turnCounter;
    }

    public String checkAllBalance() {
        List<String> equalLS = new ArrayList<>();
        Player[] players = playerController.getPlayers();
        String winner;
        checkEqualBalance(equalLS, players);
        if (equalLS.size() > 1) {
            winner = findMaxTotalBalance(equalLS, players);
        } else {
            winner = findMaxBalance(players);
        }
        return winner;
    }

    /**
     * Finds and returns the player with the biggest balance
     *
     * @return
     */
    private String findMaxBalance(Player[] players) {
        int currMax = 0;
        String currLeader = "";
        for (Player player : players) {
            if (player.getBalance() > currMax) {
                currMax = player.getBalance();
                currLeader = player.getIdentifier();
            }
        }
        return currLeader;
    }

    /**
     * Finds the maximum total value of the players with the same balance
     *
     * @param equalLS
     * @return
     */
    private String findMaxTotalBalance(List<String> equalLS, Player[] players) {
        HashMap<Player, Integer> playerProp = fieldController.playerPropertyValues();
        Player winner = new Player(99, "");
        int maxTotal = 0;
        for (Player player : players) {
            int playerBal = player.getBalance() + playerProp.get(player);
            if (maxTotal < playerBal && equalLS.contains(player.getIdentifier())) {
                maxTotal = playerBal;
                winner = player;
            }
        }
        return winner.getIdentifier();
    }

    /**
     * Adds players to the list if they have the same balance
     *
     * @param equalLS
     */
    public void checkEqualBalance(List<String> equalLS, Player[] players) {
        for (Player playerFst : players) {
            for (Player playerNxt : players) {
                if (playerFst.getBalance() == playerNxt.getBalance() && !playerFst.getIdentifier().equals(playerNxt.getIdentifier()) && !equalLS.contains(playerNxt.getIdentifier())) {
                    equalLS.add(playerNxt.getIdentifier());
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        p.hide();
        guiController.endGame();
        System.exit(0);
    }
}
