package chanceCards;

import chanceCards.DeckComposition.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DeckFromCSV {

    private ChanceCard[] deck;

    public DeckFromCSV() {
        this.deck = new ChanceCard[20];
        int deckPosition = 0;
        final String DELIMITER = ",";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("chancecards.csv"));
            reader.readLine();
            int type = 0, name = 1, minVal = 2, maxVal = 3, drawAgain = 4, character = 5, colour1 = 6, colour2 = 7, field = 8;
            String[] data;
            String currentLine;
            while((currentLine = reader.readLine()) != null || deckPosition == deck.length) {
                data = currentLine.split(DELIMITER);
                String description = "Description from language file";
                switch(data[type]) {
                    case "CharacterSpecific":
                        deck[deckPosition] = new CharacterSpecific(
                                data[name],
                                description,
                                data[character]);
                        break;
                    case "ChangeBalance":
                        deck[deckPosition]= new ChangeBalance(
                                data[name],
                                description,
                                Integer.parseInt(data[maxVal]));
                        break;
                    case "Choice":
                        deck[deckPosition]= new Choice(
                                data[name],
                                description);
                        break;
                    case "GetOutOfJail" :
                        deck[deckPosition]= new GetOutOfJail(
                                data[name],
                                description
                        );
                        break;
                    case "MoveToColour" :
                        deck[deckPosition]= new MoveToColour(
                                data[name],
                                description,
                                Color.getColor(data[colour1]),
                                Color.getColor(data[colour2])
                        );
                        break;
                    case "MoveToField" :
                        deck[deckPosition]= new MoveToField(
                                data[name],
                                description,
                                data[field]
                        );
                        break;
                    case "MoveXSteps" :
                        deck[deckPosition]= new MoveXSteps(
                                data[name],
                                description,
                                Integer.parseInt(data[minVal]),
                                Integer.parseInt(data[maxVal])
                        );
                        break;
                }
                deckPosition++;
            }
        } catch (IOException ignored) {
        }

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
}
