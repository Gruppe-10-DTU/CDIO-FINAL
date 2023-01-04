
package controllers;
import gui_fields.*;
import models.Player;
import models.fields.Field;
import models.fields.Start;

import java.awt.*;
import java.util.ArrayList;

public class GUIConverter {

    public static GUI_Player[] playerToGUI(Player[] players){
        GUI_Player[] gui_players = new GUI_Player[players.length];
        for (int i = 0; i < players.length; i++) {
            Color color = getColorChoice(players[i].getCharacter().getColor());
            GUI_Car car = new GUI_Car( color, color, GUI_Car.Type.getTypeFromString(players[i].getCharacter().getName().toUpperCase()), GUI_Car.Pattern.ZEBRA);
            gui_players[i] = new GUI_Player(players[i].getIdentifier(), players[i].getBalance(), car);
        }
        return gui_players;
    }

    private static Color getColorChoice(int color) {
        switch (color){
            case 0:
                return Color.YELLOW;
            case 1:
                return Color.blue;
            case 2:
                return Color.white;
            case 3:
                return Color.black;
            case 4:
                return Color.red;
            case 5:
                return Color.gray;
            default:
                return Color.PINK;
        }
    }

    public static GUI_Field[] fieldListToGUI(ArrayList<Field> fieldList) {
        GUI_Field[] fields = new GUI_Field[fieldList.size()];
        for (Field field : fieldList
             ) {
            switch (field.getClass().getSimpleName()) {
                case "Street": {
                    //Start.Property prop = (Start.Property) field;

                    Color fieldColor = Color.WHITE;
                    Color textColor = Color.black;

                    //Color fieldColor = null;
                    //Color textColor = null;

                    /* switch (((Start.Property) field).getColor()) {
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
                        case "PINK": {
                            fieldColor = Color.PINK;
                            textColor = Color.black;
                            break;
                        }
                        case "ORANGE": {
                            fieldColor = Color.ORANGE;
                            textColor = Color.black;
                            break;
                        }
                        case "RED": {
                            fieldColor = Color.RED;
                            textColor = Color.black;
                            break;
                        }
                        case "YELLOW": {
                            fieldColor = Color.YELLOW;
                            textColor = Color.black;
                            break;
                        }
                        case "GREEN": {
                            fieldColor = Color.GREEN;
                            textColor = Color.black;
                            break;
                        }
                        case "BLUE": {
                            fieldColor = Color.BLUE.brighter();
                            textColor = Color.white;
                            break;
                        }
                    }*/

                    fields[field.getID()] = new GUI_Street(field.getName(), " ", field.getName(), Integer.toString(1 /*prop.getPrice()*/), fieldColor, textColor);
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