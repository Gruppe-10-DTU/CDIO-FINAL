package controllers;

import chanceCards.*;
import chanceCards.Choice;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ChanceControllerTest {

    @Test
    void chanceCardTypeConversionDataRetention() {
        Deck deck = new Deck();
        for (int i = 0; i < 20; i++) {
            ChanceCard card = deck.drawCard();
            String type = card.getType();
            switch (type) {
                case "CharacterSpecific":
                    CharacterSpecific csCard = (CharacterSpecific) card;
                    assertNotNull(csCard.getCharacter());
                    break;
                case "ChangeBalance":
                    ChangeBalance cbCard = (ChangeBalance) card;
                    assertNotEquals(0, cbCard.getEffect());
                    break;
                case "Choice":
                    Choice chCard = (Choice) card;
                    break;
                case "GetOutOfJail":
                    GetOutOfJail goojCard = (GetOutOfJail) card;

                    break;
                case "MoveToColour":
                    MoveToColour mtcCard = (MoveToColour) card;
                    assertNotNull(mtcCard.getColour_1());
                    break;
                case "MoveToField":
                    MoveToField mtfCard = (MoveToField) card;
                    assertNotEquals("", mtfCard.getFieldName());
                    break;
                case "MoveXSteps":
                    MoveXSteps mxsCard = (MoveXSteps) card;
                    assertNotEquals(0, mxsCard.getMaxSteps());
                    assertNotEquals(mxsCard.getMinSteps(), mxsCard.getMaxSteps());
                    break;
            }

        }
    }
}
