package chanceCards;

public class Deck {

    private ChanceCard[] deck;

    public Deck(){
        //instanciates the array based on the amount of cards in the DeckComposition enumerator.
        this.deck = new ChanceCard[DeckComposition.values().length];

        int deckPosition = 0;
        //Iterates over the enum and creates Cards of different types based on the names
        for(DeckComposition name : DeckComposition.values()){
            String type = name.name().replaceAll("_([0-9]+)", "");
            switch(type){
                case("CHARACTER_SPECIFIC"):
                    deck[deckPosition] = new CharacterSpecific();
                        break;
                case("MOVE_TO_COLOUR"):
                    deck[deckPosition] = new MoveToColour();
                        break;
                case("MOVE_TO_FIELD"):
                    deck[deckPosition] = new MoveToField();
                        break;
                case("GET_OUT_OF_JAIL"):
                    deck[deckPosition] = new GetOutOfJail();
                        break;
                case("CHANGE_BALANCE"):
                    deck[deckPosition] = new ChangeBalance();
                        break;
                case("CHOICE"):
                    deck[deckPosition] = new Choice();
                        break;
                case("MOVE_X_STEPS"):
                    deck[deckPosition] = new MoveXSteps();
                        break;
            }
            deckPosition++;
        }
        //shuffles the deck so it is ready for a game.
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
