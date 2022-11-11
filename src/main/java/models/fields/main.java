package models.fields;

import controllers.CSVReader;
import controllers.FieldController;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class main {
    public static void main (String[] args) throws Exception {

        ArrayList<ArrayList<String>> fieldList;



        String path = System.getProperty("user.dir") + "/src/main/java/models/Fields.csv";

        CSVReader csvReader = new CSVReader(path,",",true);
         fieldList = csvReader.getDataAsArrList();

        FieldController fieldController = new FieldController(fieldList);

        HashMap<Player, Integer> value = fieldController.playerPropertyValues();
    };

}
