package chanceCards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeckTest {

    @Test
    void shuffleChangesDeckOrder() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        for (int i = 0; i < 20; i++) {
            assertNotEquals(deck1.drawCard(), deck2.drawCard());
        }
    }
    @Test
    void drawCardReturnsAnObject() {
        Deck deck = new Deck();
        for (int i = 0; i < 20; i++) {
            assertNotNull(deck.drawCard());
        }
    }
}