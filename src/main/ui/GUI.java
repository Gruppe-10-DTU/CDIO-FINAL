package game.ui;

import game.controllers.GUIConverter;
import game.controllers.GameController;
import game.models.Player;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

import javax.swing.*;
import java.awt.*;
import static javax.swing.JOptionPane.*;

public class GUI {
    GUI_Board gui;
    GameController gameController;
    private Button waitButton;
    private Button rollButton;
    private Button languageMenu;

    //TODO better way to show buttons
    public GUI(){
        gameController = new GameController();
        gui = new GUI_Board(GUIConverter.fieldToGui(gameController.getFields()));
        for (GUI_Player player: GUIConverter.playerToGUI(gameController.getPlayers())
             ) {
            gui.addPlayer(player);
        }
        waitButton = new Button("Ok");
        waitButton.addActionListener(e -> endTurn());
        rollButton = new Button("Roll");
        rollButton.setBounds(0,0,50,20);
        rollButton.addActionListener(e -> startTurn());

        languageMenu = new Button(gameController.getLanguageButton());
        languageMenu.addActionListener(e -> {
            //TODO add function to see supported language
            String[] list = new String[] {"English", "Danish"};
            String language = (String) JOptionPane.showInputDialog(
                gui, gameController.getLanguageText(), gameController.getLanguageButton(), QUESTION_MESSAGE,null,list,"English" );
            updateFields(GUIConverter.fieldToGui(gameController.updateFields(language)));
            languageMenu.setLabel(gameController.getLanguageButton());
            rollButton.setLabel(gameController.getRollButton());
            gui.clearInputPanel();
            gui.getUserInput(gameController.getRollText(),rollButton,languageMenu);
        });
        gui.getUserInput(gameController.getRollText(),rollButton,languageMenu);

    }
    private void startTurn(){
        showDice(gameController.roll());
        int sum = gameController.sum();
        boolean successTurn = gameController.turn();
        Player player = gameController.getActivePlayer();
        GUI_Player guiPlayer = gui.getPlayer(player.getIdentifier());
        gui.getFields()[sum].setCar(guiPlayer,true);
        gui.clearInputPanel();
        //TODO change new button to wait for input
        if(gameController.hasWon()){
            gameOver(player.getIdentifier());
        }else{
            guiPlayer.setBalance(player.getBalance());
            if(successTurn){
                gui.getUserInput(gui.getFields()[sum].getDescription(),waitButton);
            }else{
                gui.getUserInput((gui.getFields()[sum].getDescription() + "\r\n" + gameController.noMoney()),waitButton);
            }
        }
    }
    private void gameOver(String winner){
        JOptionPane.showConfirmDialog(gui,
                // TODO better text
                // Valg mellem luk og reset eller kun luk?
                winner + " vandt, tillykke!", "", JOptionPane.DEFAULT_OPTION);
        System.exit(0);
    }
    private void endTurn(){
        //gui.updatePlayers();
        gui.getFields()[gameController.sum()].removeAllCars();
        gui.clearInputPanel();
        gui.getUserInput(gameController.getRollText(),rollButton,languageMenu);
    }
    public void updateFields(GUI_Field[] fields){
        GUI_Field[] gameFields = gui.getFields();
        for (int i = 0; i < gameFields.length; i++) {
            gameFields[i].setTitle(fields[i].getTitle());
            gameFields[i].setDescription(fields[i].getDescription());
            gameFields[i].setSubText(fields[i].getSubText());
        }
    }
    private void showDice(int[] dice){
        gui.setDice(4, 2, dice[0],0,5, 2, dice[1],0);
    }
}
