package models;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

    public void testSetBalanceValidate() {
        Player player = new Player(0, "name");

        assertTrue(player.setBalance(-10));
        assertEquals(10, player.getBalance());
    }

    public void testSetBalanceNotNegative() {
        Player player = new Player(0, "name" );

        assertTrue(player.setBalance(-20));
        assertFalse(player.setBalance(-1));
        assertEquals(0, player.getBalance());
    }
}