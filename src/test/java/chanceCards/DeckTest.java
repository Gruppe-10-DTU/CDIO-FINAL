package chanceCards;

import models.Language;
import models.chanceCards.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {


    /**
     * Checks that no more than 20% of the cards remain in the same position after a shuffle
     */
    @Test
    void shuffleChangesDeckOrder() {
        Language language = new Language();
        Deck deck1 = new Deck(language);
        Deck deck2 = new Deck(language);
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
        Language language = new Language();
        Deck deck = new Deck(language);
        for (int i = 0; i < deck.getDeckSize(); i++) {
            assertNotNull(deck.drawCard());
        }
    }
}