import controllers.GameController;
import models.Player;

import java.util.LinkedHashMap;

public class Start {
    public static void main(String[] args) {
        GameController game = new GameController();
        game.startGame();
    }
}
