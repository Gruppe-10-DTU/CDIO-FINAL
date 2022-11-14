package chanceCards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {


    /**
     * Checks that no more than 20% of the cards remain in the same position after a shuffle
     */
    @Test
    void shuffleChangesDeckOrder() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        deck2.shuffle();
        int count = 0;
        for (int i = 0; i < deck1.getDeckSize(); i++) {
            String name1 = deck1.drawCard().getName();
            String name2 = deck2.drawCard().getName();
            if(name1.equals(name2)) count++;
        }
        assertTrue(count <= (deck1.getDeckSize()/5));
    }

    /**
     * Checks that the drawCard methods returns an instantiated chance card
     */
    @Test
    void drawCardReturnsAnObject() {
        Deck deck = new Deck();
        for (int i = 0; i < deck.getDeckSize(); i++) {
            assertNotNull(deck.drawCard());
        }
    }
}