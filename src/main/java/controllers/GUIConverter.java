
package controllers;
import gui_fields.*;
import gui_resources.Attrs;
import models.Player;
import models.fields.Field;
import models.fields.Property;

import java.awt.*;
import java.util.ArrayList;

import static gui_fields.GUI_Car.Type.UFO;

public class GUIConverter {

    public static GUI_Player[] playerToGUI(Player[] players){
        GUI_Player[] gui_players = new GUI_Player[players.length];
        for (int i = 0; i < players.length; i++) {
            GUI_Car car = new GUI_Car(Color.YELLOW , Color.GREEN, GUI_Car.Type.getTypeFromString(players[i].getCharacter().getName().toUpperCase()), GUI_Car.Pattern.ZEBRA);
            car.setPrimaryColor(Color.yellow);
            gui_players[i] = new GUI_Player(players[i].getIdentifier(), players[i].getBalance(), car);
        }
        return gui_players;
    }

    public static GUI_Field[] fieldListToGUI(ArrayList<Field> fieldList) {
        GUI_Field[] fields = new GUI_Field[fieldList.size()];
        for (Field field : fieldList
             ) {
            switch (field.getClass().getSimpleName()) {
                case "Property": {
                    Property prop = (Property) field;
                    fields[field.getID()] = new GUI_Street(field.getName(), "", "", Integer.toString(prop.getPrice()), GUI_Board.BASECOLOR, Color.getColor(prop.getColor()));
                    break;
                }
                case "Chance": {
                    fields[field.getID()] = new GUI_Chance("", field.getName(), "", Color.white, Color.black);
                    break;
                }
                case "Start": {
                    fields[field.getID()] = new GUI_Start(field.getName(), field.getName(), "", Color.white, Color.black);
                    break;
                }
                case "Jail": {
                    fields[field.getID()] = new GUI_Jail();
                    break;
                }
                case "ToJail": {
                    fields[field.getID()] = new GUI_Refuge(Attrs.getImagePath("GUI_Field.Image.GoToJail"), field.getName(),"","", GUI_Board.BASECOLOR, GUI_Board.BASECOLOR);
                    break;
                }
                default: {
                    //fields[field.getID()] = new GUI_Empty(Color.white, Color.white, "", "", "");
                    fields[field.getID()] = new GUI_Refuge("default",field.getName(), field.getName(), field.getName(), Color.white, Color.black);
                    break;
                }
            }
        }
        return fields;
    }
}