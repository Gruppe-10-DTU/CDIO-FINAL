
package controllers;
import gui_fields.*;
import models.Player;
import models.fields.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
                    Street prop = (Street) field;

                    //Color fieldColor = Color.WHITE;
                    //Color textColor = Color.black;

                    Color fieldColor = null;
                    Color textColor = null;

                    switch (((Street) field).getColor()) {
                        case "pink": {
                            fieldColor = Color.PINK;
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
                            float[] hsbValues = new float[3];
                            Color.RGBtoHSB(111,7,176, hsbValues);
                            fieldColor = Color.getHSBColor(hsbValues[0], hsbValues[1], hsbValues[2]);
                            textColor = Color.white;
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
                    fields[field.getID()] = new GUI_Jail("Default", field.getName(), field.getName(), field.getName(), new Color(125, 125, 125), Color.BLACK);
                    break;
                }
                case "ToJail": {
                    fields[field.getID()] = new GUI_Jail("Default",field.getName(),field.getName(), field.getName(),new Color(125, 125, 125), Color.BLACK);
                    break;
                }
                case "Brewery": {
                    Brewery brewery = (Brewery) field;
                    fields[field.getID()]=new GUI_Brewery("Default",brewery.getName(),brewery.getName(),brewery.getName(),Integer.toString(brewery.getRent0()),Color.white, Color.black);
                    break;
                }
                case "Ferry": {
                    Ferry ferry = (Ferry) field;
                    fields[field.getID()]=new GUI_Shipping("Default",ferry.getName(),ferry.getName(),ferry.getName(),Integer.toString(ferry.getRent0()),Color.white, Color.black);
                    break;
                }
                case "Refuge": {
                    Refuge refuge = (Refuge) field;
                    fields[field.getID()]=new GUI_Refuge("Default",refuge.getName(),refuge.getName(),refuge.getName(),Color.white, Color.black);
                    break;
                }
                case "Tax": {
                    Tax tax = (Tax) field;
                    fields[field.getID()]=new GUI_Tax(tax.getName(),tax.getName(),tax.getName(),Color.white, Color.black);
                    break;
                }
                default: {
                    //fields[field.getID()] = new GUI_Empty(Color.white, Color.white, "", "", "");
                    fields[field.getID()] = new GUI_Refuge("default",field.getName(), field.getName(), field.getName(), Color.white, Color.black);
                    break;
                }
            }
        }
        return fields ;
    }
}