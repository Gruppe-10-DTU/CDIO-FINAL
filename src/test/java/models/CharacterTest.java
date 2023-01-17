package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    Character character1;
    Character character2;
    Character character3;
    Character character4;
    Character character5;
    Character character6;

    @BeforeEach
    void setUp() {
        character1 = new Character("Albert",0);
        character2 = new Character("Bob",2);
        character3 = new Character("Charlie",3);
        character4 = new Character("Danny",4);
        character5 = new Character("Eddy",5);
        character6 = new Character("Frank",6);
    }

    @Test
    void getName() {
        assertEquals("Albert", character1.getName());
        assertEquals("Bob", character2.getName());
        assertEquals("Charlie", character3.getName());
        assertEquals("Danny", character4.getName());
        assertEquals("Eddy", character5.getName());
        assertEquals("Frank", character6.getName());
    }

    @Test
    void getColor() {
        assertEquals(Color.YELLOW, character1.getColor());
        assertEquals(Color.white, character2.getColor());
        assertEquals(Color.ORANGE, character3.getColor());
        assertEquals(Color.red, character4.getColor());
        assertEquals(Color.gray, character5.getColor());
        assertEquals(Color.pink, character6.getColor());
    }
}