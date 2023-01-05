package controllers;

import models.chanceCards.*;
import models.*;
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

    public GameController() {
        language = new Language(System.getProperty("user.language"));
        fieldController = new FieldController(language);
        guiController = new GUIController(fieldController.getFieldList());
        deck = new Deck(language);
        diceHolder = new DiceHolder(StartValues.getInstance().getValue("diceAmount"));
        playerController = new PlayerController();
        deck.shuffle();
        this.initialize();
    }

    public GameController(Language language, PlayerController playerController, FieldController fieldController, GUIController guiController, Deck deck, DiceHolder diceHolder) {
        this.language = language;
        this.playerController = playerController;
        this.fieldController = fieldController;
        this.guiController = guiController;
        this.deck = deck;
        this.diceHolder = diceHolder;
        deck.shuffle();
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

        guiController.setPlayers(playerController.getPlayers());
        while (!isOver) {
            this.currentPlayer = playerController.getPlayerById(turnCounter);
            //TakeTurn(currentPlayer);
        }
    }


    /**
     * Functions to display the winner and give the users an option to close the game
     */
/*
    private void EndGame() {
        String endWinner = playerController.getPlayerById(0).getIdentifier();
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

 */

    /*
     * Logic to handle a players turn
     * @param player Active player
     *
    public void TakeTurn(Player player) {
        try{
        if(player.getLocation() == 30){
           Jail jail = (Jail) fieldController.getField(10);
           if(jail.isInJail(player)){
               if(player.getGetOutOfJail() != null){
                   player.setGetOutOfJail(null);
                   guiController.displayMsg(language.getLanguageValue("outOfJailFree"));
               }else if(!player.setBalance(-2)){
                   EndGame();
               }else{
                   guiController.displayMsg(language.getLanguageValue("outOfJailPay", "2"));
               }
               fieldController.freePlayer(player);
               guiController.updatePlayer(player);
           }
        }
        if(player.getCharacterSpecific() != null){
            characterSpecific(player);
        }else {
            guiController.getRoll(language.getLanguageValue("rollText", player.getIdentifier()), language.getLanguageValue("rollButton"));
            diceHolder.roll();
            guiController.showRoll(diceHolder.sum());
            guiController.displayDice(diceHolder.getRolls());

           guiController.movePlayer(player)
            if (player.getLocation() + diceHolder.sum() >= 40) {
                guiController.displayMsg(language.getLanguageValue("passStart"));
            } else {
                guiController.updatePlayer(player);
            }
            landOnField(player);
        }
        turnCounter++;
    }*/
    /*
    }catch (Exception e){
            System.out.println("wah");}
    }

     */

    /*
     * Handle all logic regarding the specific character card.
     * @param player Player to take the turn
     *
    public void characterSpecific(Player player){
        player.setCharacterSpecific(null);
        if(!player.decreaseSoldSign()){
            guiController.displayMsg(language.getLanguageValue("noMoreHouses"));
            return;
        }
        Street[] propertyChoices = fieldController.getFreeFields();
        if(propertyChoices.length != 0){
            int target = guiController.getPropertyChoice(language.getLanguageValue("emtpyFieldChoice"),propertyChoices);
            int spaces;

            if (target < player.getLocation()) {
                spaces = (fieldController.getFieldList().size() - player.getLocation()) + target;
            } else {
                spaces = target - player.getLocation();
            }
            playerController.playerMove(player, spaces);
            guiController.updatePlayer(player);
            landOnField(player);
        } else{
            propertyChoices = fieldController.getFieldOtherPlayers(player);
            if(propertyChoices.length == 0){
                guiController.displayMsg(language.getLanguageValue("ownEverything"));
                return;
            }
            int target = guiController.getPropertyChoice(language.getLanguageValue("buyFieldFromPlayer"), propertyChoices);
            Street property = (Street) fieldController.getField(target);
            int spaces;
            if (target < player.getLocation()) {
                spaces = (fieldController.getFieldList().size() - player.getLocation()) + target;
            } else {
                spaces = target - player.getLocation();
            }
            if (player.setBalance(-property.getPrice())) {
                property.getOwner().setBalance(property.getPrice());
                guiController.updatePlayer(property.getOwner());
                fieldController.setOwner(player, property.getID());
                playerController.playerMove(player, spaces);
                guiController.updatePlayer(player);
                guiController.updateField(property);
                guiController.displayMsg(language.getLanguageValue("buy", Integer.toString(property.getPrice())));
            } else {
                EndGame();
            }
        }
    }*/

    /*
     * @param player All logic controlling what happens when you land on a field
     *
    private void landOnField(Player player) {
        Field field = fieldController.getField(player.getLocation());
        //Choose logic based on the field type
        switch (field.getClass().getSimpleName()) {
            case "Property": {
                Street property = (Street) field;
                if(property.getOwner() == null && player.decreaseSoldSign()){
                    guiController.displayMsg(language.getLanguageValue("fieldBuy", property.getName()));
                    if (player.setBalance(-property.getPrice())) {
                        fieldController.setOwner(player, property.getID());
                        guiController.updateField(property);
                        guiController.displayMsg(language.getLanguageValue("buy", Integer.toString(property.getPrice())));
                    } else {
                        EndGame();
                    }
                } else if (player.getSoldSign() < 0) {
                    guiController.displayMsg(language.getLanguageValue("noMoreHouses"));
                } else {
                    if(property.getOwner() == player){
                        guiController.displayMsg(language.getLanguageValue("ownField"));
                    }else {
                        guiController.displayMsg(language.getLanguageValue("fieldRent", property.getOwner().getIdentifier()));
                        if (!playerController.getRent(player, property, fieldController.sameOwner(property))) {
                            EndGame();
                        } else {
                            guiController.updatePlayer(player);
                            guiController.updatePlayer(property.getOwner());
                            int rent = fieldController.sameOwner(property) ? property.getPrice() * 2 : property.getPrice();
                            guiController.displayMsg(language.getLanguageValue("pay", Integer.toString(rent)));
                        }
                    }
                }
                break;
            }
            case "Chance": {
                guiController.displayMsg(language.getLanguageValue("fieldChance"));
                boolean drawAgain = false;
                do {
                    drawAgain = takeChance();
                }while (drawAgain);
                break;
            }
            case "Start": {
                //guiController.displayMsg(language.getLanguageValue("fieldLandStart"));
                break;
            }
            case "Empty": {
                guiController.displayMsg(language.getLanguageValue("fieldFreeParking"));
                break;
            }
            case "ToJail": {
                guiController.displayMsg(language.getLanguageValue("fieldGoToJail"));
                fieldController.jailPlayer(player);
                guiController.updatePlayer(player);
            }
            default: {
                break;
            }
        }
        guiController.updateBoard(playerController, fieldController);
    }*/

    /*public boolean takeChance(){
        ChanceCard card = deck.drawCard();
        String type = card.getType();
        guiController.showChanceCard(card.getDescription());
        String option1;
        String option2;
        String choice;
        int fieldsToMove;
        switch (type) {
            case "CharacterSpecific":
                //Add the card to the correct player, then take a new chance card
                CharacterSpecific csCard = (CharacterSpecific) card;
                playerController.addCharacterCard(csCard);
                guiController.displayMsg(language.getLanguageValue("ccDrawAgain"));
                return true;

            case "ChangeBalance":
                ChangeBalance cbCard = (ChangeBalance) card;
                int value = cbCard.getEffect();
                if (cbCard.getFromOthers()) {
                    for (Player player : playerController.getPlayers()) {
                        player.setBalance(-1 * value);
                        guiController.updatePlayer(player);
                    }
                    value *= playerController.getPlayers().length;
                }
                if(!currentPlayer.setBalance(value)){
                    EndGame();
                }
                guiController.updatePlayer(currentPlayer);
                break;

            case "ChoiceCard":
                ChoiceCard chCard = (ChoiceCard) card;
                option1 = language.getLanguageValue("ccMoveXFields", String.valueOf(chCard.getMove()));
                option2 = language.getLanguageValue("ccDrawAgain");
                choice = guiController.showChanceCardChoice(language.getLanguageValue("ccChoice"), option1, option2);
                if (choice.equals(option1)) {
                    playerController.playerMove(currentPlayer, chCard.getMove());
                    landOnField(currentPlayer);
                    guiController.updatePlayer(currentPlayer);
                } else if (choice.equals(option2)) {
                    return true;
                }
                break;

            case "GetOutOfJail":
                GetOutOfJail goojCard = (GetOutOfJail) card;
                currentPlayer.setGetOutOfJail(goojCard);
                break;

            case "MoveToColour":
                MoveToColour mtcCard = (MoveToColour) card;
                if (mtcCard.getColour_2() == null || mtcCard.getColour_2().equals("")) {
                    fieldsToMove = fieldController.moveToColor(mtcCard.getColour_1(), currentPlayer);
                } else {
                    option1 = language.getLanguageValue(mtcCard.getColour_1().toUpperCase());
                    option2 = language.getLanguageValue(mtcCard.getColour_2().toUpperCase());
                    choice = guiController.showChanceCardChoice(language.getLanguageValue("ccChoice"), option1, option2);
                    if (choice.equals(option2)) {
                        fieldsToMove = fieldController.moveToColor(mtcCard.getColour_2(), currentPlayer);
                    } else {
                        fieldsToMove = fieldController.moveToColor(mtcCard.getColour_1(), currentPlayer);
                    }
                }
                playerController.playerMove(currentPlayer, fieldsToMove);
                option1 = fieldController.getFieldList().get(currentPlayer.getLocation()).getName();
                option2 = fieldController.getFieldList().get(currentPlayer.getLocation() + 1 ).getName();
                choice = guiController.showChanceCardChoice(language.getLanguageValue("ccChoice"), option1, option2);
                if(choice.equals(option2)){
                    playerController.playerMove(currentPlayer, 1);
                }
                guiController.updatePlayer(currentPlayer);
                if (fieldController.getFreeField(currentPlayer, currentPlayer.getLocation())){
                    fieldController.setOwner(currentPlayer, currentPlayer.getLocation());
                    guiController.updateField((Street)fieldController.getField(currentPlayer.getLocation()));
                }else landOnField(currentPlayer);
                break;

            case "MoveToField":
                MoveToField mtfCard = (MoveToField) card;
                if(currentPlayer.getLocation() > mtfCard.getFieldID()){
                    fieldsToMove = (fieldController.getFieldList().size() + mtfCard.getFieldID())- currentPlayer.getLocation();
                } else {
                    fieldsToMove = mtfCard.getFieldID() - currentPlayer.getLocation();
                }
                playerController.playerMove(currentPlayer, fieldsToMove);
                guiController.updatePlayer(currentPlayer);
                landOnField(currentPlayer);
                break;

            case "MoveXSteps":
                MoveXSteps mxsCard = (MoveXSteps) card;
                int move = guiController.getXStepsToMove(language.getLanguageValue("moveXStepsChoice"), mxsCard.getMinSteps(),mxsCard.getMaxSteps());
                playerController.playerMove(currentPlayer, move);
                guiController.updatePlayer(currentPlayer);
                landOnField(currentPlayer);
                break;
        }
        guiController.updatePlayer(currentPlayer);
        return false;
    }*/
    public Integer getTurnCounter() {
        return turnCounter;
    }

    /*
     * Checks if there are more than one with the same balance then returns the one with most property, otherwise returns the one with the most balance
     * @return winner
     *
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
    }*/

    /*
     * Finds and returns the player with the biggest balance
     * @return
     *
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
    }*/

    /*
     * Finds the maximum total value of the players with the same balance
     * @param equalLS
     * @return winner
     *
    private String findMaxTotalBalance(List<String> equalLS, Player[] players) {
        HashMap<Player, Integer> playerProp = fieldController.playerPropertyValues();
        Player winner = new Player(99, "");
        int maxTotal = 0;
        for (Player player : players) {
            int playerBal = 0;
            if (playerProp.get(player) != null) playerBal = player.getBalance() + playerProp.get(player);
            if (maxTotal < playerBal && equalLS.contains(player.getIdentifier())) {
                maxTotal = playerBal;
                winner = player;
            }
        }
        return winner.getIdentifier();
    }*/

    /*
     * Adds players to the list if they have the same balance
     * @param equalLS
     *
    public void checkEqualBalance(List<String> equalLS, Player[] players) {
        for (Player playerFst : players) {
            for (Player playerNxt : players) {
                if (playerFst.getBalance() == playerNxt.getBalance() && !playerFst.getIdentifier().equals(playerNxt.getIdentifier()) && !equalLS.contains(playerNxt.getIdentifier())) {
                    equalLS.add(playerNxt.getIdentifier());
                }
            }
        }
    }*/

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
        if (playerController.getAvailablePlayers().size() == 1) {
            return true;
        } else {
            return false;
        }
    }
    public void winMsg(){
        String winner = String.valueOf(playerController.getPlayerById(0));
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

