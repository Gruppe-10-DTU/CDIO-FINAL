package controllers;

import chanceCards.ChanceCard;
import chanceCards.CharacterSpecific;
import chanceCards.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChanceControllerTest {

    @Test
    void chanceCardTypeConversionDataRetention() {
        Deck deck = new Deck();

        ChanceCard card = deck.drawCard();
        CharacterSpecific csCard = (CharacterSpecific) card;

        assertEquals("CAR",csCard.getCharacter());
    }
}