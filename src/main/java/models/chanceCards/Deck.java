package models.chanceCards;

import controllers.CSVReader;
import models.Language;

import java.util.ArrayList;

public class Deck {

    private ArrayList<ChanceCard> deck;
    private CSVReader reader;


    /**
     * Constructs deck from chanceCards.csv file in resources using custom CSVReader
     */
    public Deck(Language language) {
        final String DELIMITER = ",";

        reader = new CSVReader("/GamePack/chancecards.csv", DELIMITER, true);

        ArrayList<ArrayList<String>> cardData = reader.getDataAsArrList();

        //assigns indexes based of csv for further readability
        int type = reader.getHeaderIndex("type"),
                name = reader.getHeaderIndex("name"),
                minVal = reader.getHeaderIndex("min_value"),
                maxVal = reader.getHeaderIndex("max_value"),
                booleanModifier = reader.getHeaderIndex("boolean_modifier"),
                field = reader.getHeaderIndex("field");

        this.deck = new ArrayList<>();
        for (ArrayList<String> element: cardData) {
            String description = language.getLanguageValue("cc" + element.get(name));
            switch (element.get(type)) {
                case "Tax":
                    deck.add(new Tax(
                            element.get(name),
                            description,
                            Integer.parseInt(element.get(minVal)),
                            Integer.parseInt(element.get(maxVal))
                    ));
                    break;
                case "ChangeBalance":
                    deck.add(new ChangeBalance(
                            element.get(name),
                            description,
                            Integer.parseInt(element.get(maxVal)),
                            Boolean.parseBoolean(element.get(booleanModifier))
                    ));
                    break;
                case "MoveToFerry":
                    deck.add(new MoveToFerry(
                            element.get(name),
                            description,
                            Integer.parseInt(element.get(maxVal)),
                            Boolean.parseBoolean(element.get(booleanModifier))
                    ));
                    break;
                case "GetOutOfJail":
                    deck.add(new GetOutOfJail(
                            element.get(name),
                            description
                    ));
                    break;
                case "MoveToField":
                    deck.add(new MoveToField(
                            element.get(name),
                            description,
                            Boolean.parseBoolean(element.get(booleanModifier)),
                            Integer.parseInt(element.get(field))
                    ));
                    break;
                case "MoveXSteps":
                    deck.add(new MoveXSteps(
                            element.get(name),
                            description,
                            Integer.parseInt(element.get(maxVal))
                    ));
                    break;
                case "Grant":
                    deck.add(new Grant(
                            element.get(name),
                            description,
                            Integer.parseInt(element.get(minVal)),
                            Integer.parseInt(element.get(maxVal))
                    ));
                    break;
            }
        }
        reader = null;
    }

    /**
     * Implementation of the Fisher-Yates shuffle
     * Source: <a href="https://en.wikipedia.org/wiki/Fisher-Yates_shuffle#The_modern_algorithm">Wikipedia</a>
     */
    public void shuffle(){
        //Iterates from the back of the deck
        for(int position = deck.size() -1; position > 0; position--){

            //Generates a random number such that 0 <= randomNum <= position
            int randomNum = (int) (Math.random() * (position + 1));

            //Moves the card at the random index to the iterated position
            ChanceCard cardHolder = deck.get(randomNum);
            this.deck.add(position, cardHolder);
            this.deck.remove(randomNum);
        }
    }

    /**
     * Draw the top card from the deck, removing it from the deck
     * @return returns the ChanceCard at index 0 in the deck
     */
    public ChanceCard drawCard(){
        //Draws the top card
        ChanceCard drawnCard = deck.get(0);

        this.deck.remove(0);

        return drawnCard;
    }
    public int getDeckSize(){
        return this.deck.size();
    }

    public void returnToDeck(ChanceCard card){
        this.deck.add(card);
    }

    /**
     * A method for testing specific cards in a game scenario
     * @param Offset changes the drawn card
     */
    public void rigDeck(int Offset) {
        for (int j = 0; j < Offset; j++) {
            returnToDeck(drawCard());
        }
    }
}
