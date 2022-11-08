package controllers;

import chanceCards.*;
import chanceCards.Choice;
import models.Player;

import java.awt.*;
import java.io.FileNotFoundException;

public class ChanceController {

    private DeckFromCSV deck;

    public ChanceController(){
        this.deck = new DeckFromCSV();
        deck.shuffle();
    }
    public void takeChance(Player player){
        ChanceCard card = deck.drawCard();

        switch (card.getType()){
            case "CharacterSpecific":
                break;
            case "ChangeBalance":
                break;
            case "Choice":
                break;
            case "GetOutOfJail":
                break;
            case "MoveToColour":
                break;
            case "MoveToField":
                break;
            case "MoveXSteps":
                break;

        }
    }
}
