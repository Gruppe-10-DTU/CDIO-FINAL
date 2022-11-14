package controllers;

import models.Player;
import models.fields.Jail;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FieldControllerTest {



    @Test
    void construct() {

    }


    public Player createMockPlayer() {
        Player mockPlayer = new Player("mockPlayer");

        return mockPlayer;
    }

    @Test
        void jailPlayer() {
            ArrayList<Object> mockFieldData = new ArrayList<>();
            Player player1 = new Player(0,"Svend");
            Jail mockJail = new Jail();
            mockJail.setID(10);
            mockFieldData.add(mockJail);
            mockJail.setInJailAdd(player1);
            assertEquals(true, mockJail.isInJail(player1));
            mockJail.setInJailRemove(player1);
            assertEquals(false,mockJail.isInJail(player1));
        }
    }

    @Test
    void playerPropertyValues() {

    }

    @Test
    void moveToColor() {
    }

    @Test
    void setOwner() {
    }

    @Test
    void getFieldList() {
    }
}