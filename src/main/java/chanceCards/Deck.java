package chanceCards;

import controllers.CSVReader;
import models.Language;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Deck {

    private ChanceCard[] deck;
    private CSVReader reader;


    /**
     * Constructs deck from chanceCards.csv file in resources using custom CSVReader
     */
    public Deck(Language language) {
        final String DELIMITER = ",";

        try {
            reader = new CSVReader(System.getProperty("user.dir")+ "/src/main/resources/chancecards.csv", DELIMITER, true);
        }catch(FileNotFoundException ignored){
        }

        ArrayList<ArrayList<String>> cardData = reader.getDataAsArrList();

        //assigns indexes based of csv for further readability
        int type = reader.getHeaderIndex("type"),
                name = reader.getHeaderIndex("name"),
                minVal = reader.getHeaderIndex("min_value"),
                maxVal = reader.getHeaderIndex("max_value"),
                booleanModifier = reader.getHeaderIndex("boolean_modifier"),
                character = reader.getHeaderIndex("character"),
                colour1 = reader.getHeaderIndex("colour_1"),
                colour2 = reader.getHeaderIndex("colour_2"),
                field = reader.getHeaderIndex("field");

        this.deck = new ChanceCard[cardData.size()];
        int deckPosition = 0;
        for (ArrayList<String> element: cardData) {
            String description = language.getLanguageValue("cc" + element.get(name));
            switch (element.get(type)) {
                case "CharacterSpecific":
                    deck[deckPosition] = new CharacterSpecific(
                            element.get(name),
                            description,
                            element.get(character)
                    );
                    break;
                case "ChangeBalance":
                    deck[deckPosition] = new ChangeBalance(
                            element.get(name),
                            description,
                            Integer.parseInt(element.get(maxVal)),
                            Boolean.getBoolean(element.get(booleanModifier))
                    );
                    break;
                case "Choice":
                    deck[deckPosition] = new Choice(
                            element.get(name),
                            description,
                            Integer.parseInt(element.get(maxVal)),
                            Boolean.getBoolean(element.get(booleanModifier))
                    );
                    break;
                case "GetOutOfJail":
                    deck[deckPosition] = new GetOutOfJail(
                            element.get(name),
                            description
                    );
                    break;
                case "MoveToColour":
                    deck[deckPosition] = new MoveToColour(
                            element.get(name),
                            description,
                            element.get(colour1),
                            element.get(colour2)
                    );
                    break;
                case "MoveToField":
                    deck[deckPosition] = new MoveToField(
                            element.get(name),
                            description,
                            Integer.parseInt(element.get(field))
                    );
                    break;
                case "MoveXSteps":
                    deck[deckPosition] = new MoveXSteps(
                            element.get(name),
                            description,
                            Integer.parseInt(element.get(minVal)),
                            Integer.parseInt(element.get(maxVal))
                    );
                    break;
            }
            deckPosition++;
        }
        reader = null;
    }

    /**
     * Implementation of the Fisher-Yates shuffle
     * Source: <a href="https://en.wikipedia.org/wiki/Fisher-Yates_shuffle#The_modern_algorithm">Wikipedia</a>
     */
    public void shuffle(){
        //Iterates from the back of the deck
        for(int position = deck.length -1; position > 0; position--){

            //Generates a random number such that 0 <= randomNum <= position
            int randomNum = (int) (Math.random() * (position + 1));

            //Swaps the card at the random index and the iterated position
            ChanceCard cardHolder = deck[position];
            deck[position] = deck[randomNum];
            deck[randomNum] = cardHolder;
        }
    }

    /**
     * Draw the top card from the deck and put it back at the bottom
     * @return returns the ChanceCard at index 0 in the deck
     */
    public ChanceCard drawCard(){
        //Draws the top card
        ChanceCard drawnCard = deck[0];

        //Moves up the rest of the array
        for (int i = 1; i < deck.length; i++) {
            deck[i - 1] = deck[i];
        }

        //Puts the card back at the bottom
        deck[deck.length - 1] = drawnCard;

        return drawnCard;
    }
    public int getDeckSize(){
        return this.deck.length;
    }
}
