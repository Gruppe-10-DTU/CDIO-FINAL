package main.chanceCards;

public class Deck {

    private ChanceCard[] deck;

    public Deck(){
        //instanciates the array based on the amount of cards in the DeckComposition enumerator.
        this.deck = new ChanceCard[DeckComposition.values().length];

        int deckPosition = 0;
        //Iterates over the enum and creates Cards of different types based on the names
        for(DeckComposition card : DeckComposition.values()){
            String type = card.getType();
            switch(type){
                case("CharacterSpecific"):
                    deck[deckPosition] = new CharacterSpecific(card.name(), "[REPLACE]" ); //replace with language path
                        break;
                case("MoveToColour"):
                    deck[deckPosition] = new MoveToColour(card.name(), "[REPLACE]" ); //replace with language path
                        break;
                case("MoveToField"):
                    deck[deckPosition] = new MoveToField(card.name(), "[REPLACE]" ); //replace with language path
                        break;
                case("GetOutOfJail"):
                    deck[deckPosition] = new GetOutOfJail(card.name(), "[REPLACE]" ); //replace with language path
                        break;
                case("ChangeBalance"):
                    deck[deckPosition] = new ChangeBalance(card.name(), "[REPLACE]" ); //replace with language path
                        break;
                case("Choice"):
                    deck[deckPosition] = new Choice(card.name(), "[REPLACE]" ); //replace with language path
                        break;
                case("MoveXSteps"):
                    deck[deckPosition] = new MoveXSteps(card.name(), "[REPLACE]" ); //replace with language path
                        break;
            }
            deckPosition++;
        }
        //shuffles the deck, so it is ready for a game.
        shuffle();
    }

    /**
     * Implementation of the Fisher-Yates shuffle
     * Source: <a href="https://en.wikipedia.org/wiki/Fisher-Yates_shuffle#The_modern_algorithm">Wikipedia</a>
     */
    private void shuffle(){
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
}
