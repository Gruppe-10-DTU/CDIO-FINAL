package game.controllers;

import game.models.*;

public class GameController {
    DiceHolder diceHolder = new DiceHolder();
    private final int WINCONDITION = 3000;
    private Player[] players;
    private Field[] fields;
    private int turnCounter = 0;
    private Language language;
    public GameController(){
        this.language = new Language();
        players = new Player[2];
        players[0]=new Player("Player 1");
        players[1] = new Player("Player 2");
        fields = new Field[12];
        int[] effects = new int[]{0,250, -100, 100,-20,180,0,-70,60,-80,-50,650};
        for (int i = 0; i < effects.length; i++) {
            fields[i] = new Field(effects[i], this.language.getLanguageValue("fieldName"+(i+1)), this.language.getLanguageValue("field"+(i+1)));
        }
    }
    public Player[] getPlayers(){
        return players;
    }
    public Field[] getFields(){
        return fields;
    }
    public int[] roll(){
        diceHolder.roll();
        return diceHolder.getRolls();
    }
    //Turn function for the roll button
    public boolean turn(){
        Player player = players[turnCounter % 2];
        boolean returnValue = player.setBalance(fields[diceHolder.sum()-2].getEffect());
        //If the player hasn't won and didn't roll 10, increase the turn
        if(!hasWon() && diceHolder.sum() != 10){
            turnCounter++;
        }
        return returnValue;
    }
    public boolean hasWon(){
        return players[turnCounter%2].getBalance() >= WINCONDITION;
    }
    public int sum(){
        return diceHolder.sum() - 1;
    }
    public Player getActivePlayer(){
        return players[turnCounter%2];
    }
    //Get the text for the language button
    public String getLanguageButton() {
        return language.getLanguageValue("languageButton");
    }
    public Field[] updateFields(String newLanguage) {
        //Set the new language
        language.updateLanguage(newLanguage);
        //Update each field
        for (int i = 0; i < fields.length; i++) {
            fields[i].setName(language.getLanguageValue("fieldName"+(i+1)));
            fields[i].setDescription( language.getLanguageValue("field"+(i+1)));
        }
        //Return the new fields, converted to GUI_Field
        return getFields();
    }
    //Helper function for roll button
    public String getRollButton() {
        return language.getLanguageValue("rollButton");
    }
    //Get the roll text
    public String getRollText() {
        return language.getLanguageValue("start",players[turnCounter % 2].getIdentifier());
    }

    public Object getLanguageText() {
        return language.getLanguageValue("LanguageManuText");
    }

    public String noMoney() {
        return language.getLanguageValue("isZero");
    }
    public Integer getTurnCounter() {
        return turnCounter;
    }
}
