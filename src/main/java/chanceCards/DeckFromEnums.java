package chanceCards;

import chanceCards.DeckComposition.*;

public class DeckFromEnums {

    private ChanceCard[] deck;

    public DeckFromEnums(){
        //instanciates the array based on the amount of cards in the DeckComposition package.
        int deckSize = ChangeBalanceCards.values().length
                + CharacterSpecificCards.values().length
                + ChoiceCards.values().length
                + GetOutOfJailCards.values().length
                + MoveToColourCards.values().length
                + MoveToFieldCards.values().length
                + MoveXStepsCards.values().length;

        this.deck = new ChanceCard[deckSize];

        int deckPosition = 0;

        //Iterates over the enums and creates Cards of different types
        for(ChangeBalanceCards card : ChangeBalanceCards.values()){
            deck[deckPosition] = new ChangeBalance(card.name(), "DESCRIPTION", card.getAmount());
            deckPosition++;
        }
        for(CharacterSpecificCards card : CharacterSpecificCards.values()){
            deck[deckPosition] = new CharacterSpecific(card.name(), "DESCRIPTION", card.getCharName());
            deckPosition++;
        }
        for(ChoiceCards card : ChoiceCards.values()){
            deck[deckPosition] = new Choice(card.name(), "DESCRIPTION"); //MUST BE UPDATED!
            deckPosition++;
        }
        for(GetOutOfJailCards card : GetOutOfJailCards.values()){
            deck[deckPosition] = new GetOutOfJail(card.name(), "DESCRIPTION"); //MUST BE UPDATED!
            deckPosition++;
        }
        for(MoveToColourCards card : MoveToColourCards.values()){
            deck[deckPosition] = new MoveToColour(card.name(), "DESCRIPTION", card.getColour1(), card.getColour2());
            deckPosition++;
        }
        for(MoveToFieldCards card : MoveToFieldCards.values()){
            deck[deckPosition] = new MoveToField(card.name(), "DESCRIPTION", card.getFieldName());
            deckPosition++;
        }
        for(MoveXStepsCards card : MoveXStepsCards.values()){
            deck[deckPosition] = new MoveXSteps(card.name(), "DESCRIPTION", card.getMinValue(), card.getMaxValue()); //MUST BE UPDATED!
            deckPosition++;
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
