package controllers;

import models.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    Player player1 = new Player(0, "TestPlayer1");
    Player player2 = new Player(1, "TestPlayer2");
    List<String> equalLS = new ArrayList<>();
    Player[] players = {player1,player2};
    GameController GC = new GameController();

    @Test
    void testBalance() {

    }

}