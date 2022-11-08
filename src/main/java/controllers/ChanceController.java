package controllers;

import chanceCards.*;
import chanceCards.Choice;
import models.Player;

import java.awt.*;
import java.io.FileNotFoundException;

public class ChanceController {

    private DeckFromCSV deck;

    public ChanceController(){
        try {
            this.deck = new DeckFromCSV();
        } catch (FileNotFoundException ignored){
        }
        deck.shuffle();
    }
    public void takeChance(Player player){
        ChanceCard card = deck.drawCard();

        switch (card.getType()){
            case "CharacterSpecific":

                break;
            case "ChangeBalance":
                player.setBalance(card.getEffect());
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
