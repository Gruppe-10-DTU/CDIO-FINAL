package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanguageTest {
    Language language;

    @BeforeEach
    void setUp() {
        language = new Language("da");
    }

    @Test
    void getLanguageValue() {
        assertEquals("ok", language.getLanguageValue("buttonOk"));
    }

    @Test
    void testGetLanguageValue() {
        String expected = "De har passeret start modtag 25";
        assertEquals(expected, language.getLanguageValue("passStart", "25"));
    }

    @Test
    void updateLanguage() {
        language.updateLanguage("en");
        String expected = "You have passed start and receive 25";
        assertEquals(expected, language.getLanguageValue("passStart", "25"));
    }
}