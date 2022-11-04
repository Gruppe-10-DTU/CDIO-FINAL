package chanceCards;

public class Deck {

    private ChanceCard[] deck;

    public Deck(){

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
