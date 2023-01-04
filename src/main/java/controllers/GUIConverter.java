
package controllers;
import gui_fields.*;
import models.Player;
import models.fields.Field;
import models.fields.Start;
import models.fields.Street;

import java.awt.*;
import java.util.ArrayList;

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
                case "Street": {
                    Street prop = (Street) field;

                    //Color fieldColor = Color.WHITE;
                    //Color textColor = Color.black;

                    Color fieldColor = null;
                    Color textColor = null;

                    switch (((Street) field).getColor()) {
                        case "BROWN": {
                            float[] hsbValues = new float[3];
                            Color.RGBtoHSB(102,77,44, hsbValues);
                            fieldColor = Color.getHSBColor(hsbValues[0], hsbValues[1], hsbValues[2]);
                            textColor = Color.white;
                            break;
                        }
                        case "CYAN": {
                            fieldColor = Color.CYAN;
                            textColor = Color.black;
                            break;
                        }
                        case "pink": {
                            fieldColor = Color.PINK;
                            textColor = Color.black;
                            break;
                        }
                        case "ORANGE": {
                            fieldColor = Color.ORANGE;
                            textColor = Color.black;
                            break;
                        }
                        case "red": {
                            fieldColor = Color.RED;
                            textColor = Color.black;
                            break;
                        }
                        case "yellow": {
                            fieldColor = Color.YELLOW;
                            textColor = Color.black;
                            break;
                        }
                        case "green": {
                            fieldColor = Color.GREEN;
                            textColor = Color.black;
                            break;
                        }
                        case "blue": {
                            fieldColor = Color.BLUE.brighter();
                            textColor = Color.white;
                            break;
                        }
                        case "grey": {
                            fieldColor = Color.GRAY;
                            textColor = Color.black;
                            break;
                        }
                        case "white": {
                            fieldColor = Color.WHITE;
                            textColor = Color.black;
                            break;
                        }
                        case "purple": {
                            fieldColor = Color.ORANGE;
                            textColor = Color.black;
                            break;
                        }
                    }

                    fields[field.getID()] = new GUI_Street(field.getName(), " ", field.getName(), Integer.toString(prop.getPrice()), fieldColor, textColor);
                    break;
                }
                case "Chance": {
                    fields[field.getID()] = new GUI_Chance("", field.getName(), "", Color.white, Color.black);
                    break;
                }
                case "Start": {
                    fields[field.getID()] = new GUI_Start(field.getName(), "", "", Color.white, Color.black);
                    break;
                }
                case "Jail": {
                    fields[field.getID()] = new GUI_Jail("Default",field.getName(),field.getName(), field.getName(),new Color(125, 125, 125), Color.BLACK);
                    break;
                }
                case "ToJail": {
                    fields[field.getID()] = new GUI_Jail("Default",field.getName(),field.getName(), field.getName(),new Color(125, 125, 125), Color.BLACK);
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