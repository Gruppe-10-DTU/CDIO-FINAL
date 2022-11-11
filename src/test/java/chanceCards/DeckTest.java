package chanceCards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {


    @Test
    void shuffleChangesDeckOrder() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        deck2.shuffle();
        int count = 0;
        for (int i = 0; i < 20; i++) {
            String name1 = deck1.drawCard().getName();
            String name2 = deck2.drawCard().getName();
            if(name1.equals(name2)) count++;
        }
        assertTrue(count <= 4);
    }
    @Test
    void drawCardReturnsAnObject() {
        Deck deck = new Deck();
        for (int i = 0; i < 20; i++) {
            assertNotNull(deck.drawCard());
        }
    }
}