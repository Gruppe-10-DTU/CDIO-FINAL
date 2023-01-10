package models;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class PlayerTest extends TestCase {
@Test
    public void testSetBalanceValidate() {
        Player player = new Player(0, "name");

        assertTrue(player.setBalance(-10));
        assertEquals(29990, player.getBalance());
    }
@Test
    public void testSetBalanceNotNegative() {
        Player player = new Player(0, "name" );

        assertTrue(player.setBalance(-30000));
        assertFalse(player.setBalance(-1));
        assertEquals(0, player.getBalance());
    }
}