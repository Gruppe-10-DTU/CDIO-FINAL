package controllers;

import models.Language;
import models.Player;

public class GameControllerTester extends GameController {

    FieldController FC;
    PlayerController PC;
    public GameControllerTester() {
        Language language = new Language();
        FC = new FieldController(language);
        PC = new PlayerController(3);
    }
    public void addPlayers(Player[] players){
            PC.addPlayer(0, "UFO", players[0].getIdentifier());
            PC.addPlayer(1, "UFO", players[1].getIdentifier());
            PC.addPlayer(2, "UFO", players[2].getIdentifier());
    }
}
