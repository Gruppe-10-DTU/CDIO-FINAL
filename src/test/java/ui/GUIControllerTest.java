package ui;

import junit.framework.TestCase;
import models.Character;
import models.Player;

public class GUIControllerTest extends TestCase {


    public void testUpdatePlayer() {
        GUIController gui = new GUIController();
        Character character = new Character("UFO", "");
        Player[] player = new Player[] {new Player(0,"testPlayer",0, character )};
        gui.setPlayers(player);
        gui.displayError("Balance 0");
        player[0].setBalance(5);
        gui.updatePlayer(player[0]);
        gui.displayError("New balance 5");
    }

    public void testMovePlayer() {
        GUIController gui = new GUIController();
        Character character = new Character("UFO", "");
        Player[] player = new Player[] {new Player(0,"testPlayer",0, character )};
        gui.setPlayers(player);
        player[0].setLocation(0,0);
        gui.updatePlayer(player[0]);
        gui.displayError("set at 0");
        player[0].setLocation(0,5);
        gui.updatePlayer(player[0]);
        gui.displayError("Set at 5");
    }
}