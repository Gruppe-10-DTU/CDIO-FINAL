package models;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class BalanceTest extends TestCase {
@Test
    public void testGetBalance() {
        Balance balance = new Balance(20);
        assertEquals(20, balance.getBalance());
    }
@Test
    public void testSetBalance() {
        Balance balance = new Balance(20);
        balance.setBalance(10);
        assertEquals(10,balance.getBalance());
        balance.setBalance(2);
        assertEquals(2, balance.getBalance());
    }
}