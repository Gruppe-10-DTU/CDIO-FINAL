package controllers;

import chanceCards.*;
import models.Player;


public class ChanceController {

    private Deck deck;

    public ChanceController(){
        this.deck = new Deck();
        deck.shuffle();
    }
    public ChanceController(boolean TestMode){
        this.deck = new Deck();
        if(!TestMode){
            deck.shuffle();
        }
    }
    public void takeChance(Player player){
        ChanceCard card = deck.drawCard();
        String type = card.getType().replaceAll("class chanceCards.", "");
        switch (type){
            case "CharacterSpecific":
                CharacterSpecific csCard = (CharacterSpecific) card;
                break;
            case "ChangeBalance":
                ChangeBalance cbCard = (ChangeBalance) card;
                break;
            case "Choice":
                Choice chCard = (Choice) card;
                break;
            case "GetOutOfJail":
                GetOutOfJail goojCard = (GetOutOfJail) card;
                break;
            case "MoveToColour":
                MoveToColour mtcCard = (MoveToColour) card;
                break;
            case "MoveToField":
                MoveToField mtfCard = (MoveToField) card;
                break;
            case "MoveXSteps":
                MoveXSteps mxsCard = (MoveXSteps) card;
                break;

        }
    }
}
