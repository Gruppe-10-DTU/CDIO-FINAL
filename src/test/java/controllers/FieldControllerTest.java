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

        Jail mockJail = new Jail();
        mockJail.setID(10);
        mockFieldData.add(mockJail);


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