package controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {
    CSVReader reader;

    @BeforeEach
    void setUp() {
        try{
            reader = new CSVReader(System.getProperty("user.dir") + "/src/test/resources/csvTestData.csv",",", true);
        }catch (FileNotFoundException fileNotFoundException){
            fail();
        }
    }

    @AfterEach
    void tearDown() {
        reader = null;
    }

    @Test
    void outputDataAsArrayList() {
        assertEquals(reader.getDataAsArrList().get(0).get(0),"String1.1");
        assertEquals(reader.getDataAsArrList().get(1).get(1),"String2.2");
        assertEquals(reader.getDataAsArrList().get(2).get(2),"String3.3");

    }

    @Test
    void outputDataAsString() {
        String expected = "String1.1, String1.2, String1.3, \nString2.1, String2.2, String2.3, \nString3.1, String3.2, String3.3, \n";
        assertEquals(expected,reader.toString());
    }

    @Test
    void outputDataAsArray() {
        assertEquals(reader.getData()[0][0],"String1.1");
        assertEquals(reader.getData()[1][1],"String2.2");
        assertEquals(reader.getData()[2][2],"String3.3");
    }

    @Test
    void outputHeadersCorrectly() {
        assertEquals(reader.getHeaders()[0],"Header1");
        assertEquals(reader.getHeaders()[1],"Header2");
        assertEquals(reader.getHeaders()[2],"Header3");
    }

    @Test
    void retrievesCorrectHeaderIndex() {
        assertEquals(reader.getHeaderIndex("Header1"), 0);
        assertEquals(reader.getHeaderIndex("Header2"), 1);
        assertEquals(reader.getHeaderIndex("Header3"), 2);
        assertEquals(reader.getHeaderIndex("Not a header"), -1);
    }
}