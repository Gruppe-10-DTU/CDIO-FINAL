package game.controllers;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import game.models.*;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;

import java.awt.*;

import static gui_fields.GUI_Car.Type.UFO;

public class GUIConverter {

    public static GUI_Field[] fieldToGui(Field[] fields){
        GUI_Field[] guiFields = new GUI_Field[fields.length];
        for (int i = 0; i < fields.length; i++) {
            guiFields[i] = new GUI_Street(fields[i].getName(), "",fields[i].getDescription(), Integer.toString(fields[i].getEffect()), Color.white, Color.black );
        }
        return guiFields;
    }
    public static GUI_Player[] playerToGUI(Player[] players){
        GUI_Player[] gui_players = new GUI_Player[players.length];
        for (int i = 0; i < players.length; i++) {
            GUI_Car car = new GUI_Car(Color.YELLOW , Color.GREEN, UFO, GUI_Car.Pattern.ZEBRA);
            car.setPrimaryColor(Color.yellow);
            gui_players[i] = new GUI_Player(players[i].getIdentifier(), players[i].getBalance(), car);
        }
        return gui_players;
    }

}
