package main.models;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

    public void testSetBalanceValidate() {
        Character ch = new Character("test", "img0.jpg");
        Player player = new Player("name",ch );

        assertEquals(true, player.setBalance(-10));
        assertEquals(10, player.getBalance());
    }

    public void testSetBalanceNotNegative() {
        Character ch = new Character("test", "img0.jpg");
        Player player = new Player("name",ch );

        assertEquals(true, player.setBalance(-20));
        assertEquals(false, player.setBalance(-1));
        assertEquals(0, player.getBalance());
    }
}