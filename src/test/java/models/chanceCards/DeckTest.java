package models.chanceCards;

import models.Language;
import models.chanceCards.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck;

    @BeforeEach
    void setup(){
        deck = new Deck(new Language());
    }


    /**
     * Checks that no more than 20% of the cards remain in the same position after a shuffle
     */
    @Test
    void shuffleChangesDeckOrder() {
        Deck controlDeck = new Deck(new Language());
        deck.shuffle();
        int count = 0;
        for (int i = 0; i < deck.getDeckSize(); i++) {
            String name1 = deck.drawCard().getName();
            String name2 = controlDeck.drawCard().getName();
            if(name1.equals(name2)) count++;
        }
        assertTrue(count <= (deck.getDeckSize()/5));
    }

    /**
     * Checks that the drawCard methods returns an instantiated chance card
     */
    @Test
    void drawCardReturnsAnObject() {
        for (int i = 0; i < deck.getDeckSize(); i++) {
            assertNotNull(deck.drawCard());
        }
    }
    @Test
    @DisplayName("Drawing card removes it from deck")
    void drawCardRemovesCardFromDeck(){
        int startSize = deck.getDeckSize();
        deck.drawCard();
        assertTrue(startSize > deck.getDeckSize());
    }

    @Test
    @DisplayName("Cards can be returned to deck")
    void returnToDeck(){
        ChanceCard card = new GetOutOfJail("Test", "test");
        int startSize = deck.getDeckSize();
        deck.returnToDeck(card);
        assertTrue(startSize < deck.getDeckSize());
    }
}